package com.amangarg.lid.data.repository

import android.content.Context
import android.util.Log
import androidx.room.withTransaction
import com.amangarg.lid.data.local.AppDatabase
import com.amangarg.lid.data.local.model.Question
import com.amangarg.lid.data.local.model.State
import com.amangarg.lid.data.local.model.Translation
import com.amangarg.lid.data.local.PreferenceManager
import com.amangarg.lid.data.util.extractStateCodeFromNum
import com.amangarg.lid.data.util.questionDtoToQuestion
import com.amangarg.lid.data.util.questionDtoToTranslations
import com.amangarg.lid.data.util.readQuestionsFromAsset
import com.amangarg.lid.domain.repository.QuestionRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
    @ApplicationContext private val context: Context,
    private val preferenceManager: PreferenceManager
): QuestionRepository {

    private val stateDao = appDatabase.stateDao()
    private val questionDao = appDatabase.questionDao()
    private val translationDao = appDatabase.translationDao()

    /**
     * Here I'm first checking if db is empty or not. If it is not emptu then I'm skipping import
     * If db is empty then first read from the JSON asset file.
     * Then put questions in German langugae in the Question table
     * & then add all the translations of this questions in the translation table.
     * Also crete a separate table for the states & it's code
     */
    override suspend fun importFromAsset(assetFilename: String) {
        withContext(Dispatchers.IO) {
            val count = questionDao.getQuestionCount()
            if (count > 0) {
                Log.d("QuestionRepository", "Database not empty (count=$count). Skipping import.")
                return@withContext
            }

            val listOfQuestionDto = try {
                readQuestionsFromAsset(context, assetFilename)
            } catch (e: Exception) {
                Log.e("QuestionRepository", "Error reading JSON", e)
                return@withContext
            }
            
            appDatabase.withTransaction {
                val stateCache = mutableMapOf<String, Long>()

                for (questionDto in listOfQuestionDto) {
                    val code = extractStateCodeFromNum(questionDto.num)
                    val stateId: Long? = if (code != null) {

                        stateCache[code] ?: run {

                            val existing = stateDao.getByCode(code)
                            val newId = if (existing != null) {
                                existing.id
                            } else {

                                val inserted = stateDao.insert(State(code = code, name = null))
                                if (inserted == -1L) {
                                    stateDao.getByCode(code)?.id ?: -1L
                                } else inserted
                            }
                            stateCache[code] = newId
                            newId
                        }
                    } else null

                    val questionEntity = questionDtoToQuestion(questionDto, stateId)
                    questionDao.insert(questionEntity)

                    val translations = questionDtoToTranslations(questionDto)
                    if (translations.isNotEmpty()) {
                        translationDao.insertAll(translations)
                    }
                }
            }
        }
    }

    override suspend fun getQuestionById(questionId: String): Flow<List<Translation>> {
       return translationDao.getForQuestion(questionId)
    }

    override suspend fun getQuestion(): Flow<Question?> {
        return translationDao.getQuestion()
    }

    override suspend fun saveLastQuestionId(id: String) {
        preferenceManager.saveLastQuestionId(id)
    }

    override fun getLastQuestionId(): Flow<String?> {
        return preferenceManager.getLastQuestionId()
    }

    override suspend fun getQuestionEntity(id: String): Question? {
        return questionDao.getById(id)
    }
}