package com.amangarg.lid.domain.repository

import com.amangarg.lid.data.local.model.Question
import com.amangarg.lid.data.local.model.Translation
import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    suspend fun importFromAsset(assetFilename: String)
    suspend fun getQuestionById(questionId: String): Flow<List<Translation>>
    suspend fun getQuestion(): Flow<Question?>
    suspend fun saveLastQuestionId(id: String)
    fun getLastQuestionId(): Flow<String?>
    suspend fun getQuestionEntity(id: String): Question?
}