package com.amangarg.lid.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey val id: String, // from JSON "id" (hash)
    val num: String, // "1" or "HB-1"
    val question: String,
    val a: String,
    val b: String,
    val c: String,
    val d: String,
    val solution: String,
    val image: String?,
    val category: String?,
    val context: String?,
    @ColumnInfo(name = "state_id") val stateId: Long?,  // FK to states.id (nullable)
    @ColumnInfo(name = "is_state_specific") val isStateSpecific: Boolean = false,
    val difficulty: Int? = null
)