package com.amangarg.lid.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey @ColumnInfo(name = "question_id") val questionId: String, // FK -> questions.id
    val userAnswer: String? = null, // 'a' / 'b' / 'c' / 'd'
    val isCorrect: Boolean = false,
    val timesAttempted: Int = 0,
    val lastAnswered: Long? = null, //timestamp
    val isBookmarked: Boolean = false,
    val mockExamIncluded: Boolean = false
)