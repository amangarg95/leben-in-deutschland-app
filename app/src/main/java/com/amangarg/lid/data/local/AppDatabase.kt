package com.amangarg.lid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amangarg.lid.data.local.dao.QuestionDao
import com.amangarg.lid.data.local.dao.StateDao
import com.amangarg.lid.data.local.dao.TranslationDao
import com.amangarg.lid.data.local.dao.UserProgressDao
import com.amangarg.lid.data.local.model.Question
import com.amangarg.lid.data.local.model.State
import com.amangarg.lid.data.local.model.Translation
import com.amangarg.lid.data.local.model.UserProgress

@Database(
    entities = [State::class, Question::class, Translation::class, UserProgress::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stateDao(): StateDao
    abstract fun questionDao(): QuestionDao
    abstract fun translationDao(): TranslationDao
    abstract fun userProgressDao(): UserProgressDao
    companion object {
        const val DATABASE_NAME = "lid_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration(false)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}