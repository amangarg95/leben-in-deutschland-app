package com.amangarg.lid.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amangarg.lid.data.local.model.State
import kotlinx.coroutines.flow.Flow

@Dao
interface StateDao {
    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insert(state: State): Long

    @Query("SELECT * FROM states WHERE code = :code LIMIT 1")
    suspend fun getByCode(code: String): State?

    @Query("SELECT * FROM states")
    fun allStates(): Flow<List<State>>
}