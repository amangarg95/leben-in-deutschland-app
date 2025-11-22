package com.amangarg.lid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amangarg.lid.data.local.model.Question
import com.amangarg.lid.data.local.model.Translation
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(translation: Translation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Translation>)

    @Query("SELECT * FROM translations WHERE question_id = :questionId")
    fun getForQuestion(questionId: String): Flow<List<Translation>>

    @Query("SELECT * FROM questions ORDER BY num ASC LIMIT 1")
    fun getQuestion(): Flow<Question?>
}