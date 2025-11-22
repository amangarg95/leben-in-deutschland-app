package com.amangarg.lid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amangarg.lid.data.local.model.UserProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(progress: UserProgress)

    @Query("SELECT * FROM user_progress WHERE question_id = :questionId")
    suspend fun get(questionId: String): UserProgress?

    @Query("SELECT * FROM user_progress")
    fun allProgress(): Flow<List<UserProgress>>
}