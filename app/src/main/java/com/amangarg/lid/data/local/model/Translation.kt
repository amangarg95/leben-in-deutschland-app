package com.amangarg.lid.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "translations")
data class Translation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "question_id") val questionId: String, // FK -> questions.id
    @ColumnInfo(name = "language_code") val languageCode: String,
    val question: String?,
    val a: String?,
    val b: String?,
    val c: String?,
    val d: String?,
    val context: String?
)