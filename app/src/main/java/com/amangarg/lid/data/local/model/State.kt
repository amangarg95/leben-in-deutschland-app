package com.amangarg.lid.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "states")
data class State(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val code: String, // e.g. "HB", "BW"
    val name: String? = null
)