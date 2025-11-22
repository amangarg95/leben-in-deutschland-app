package com.amangarg.lid.di

import android.content.Context
import com.amangarg.lid.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideQuestionDao(db: AppDatabase) = db.questionDao()

    @Provides
    fun provideTranslationDao(db: AppDatabase) = db.translationDao()

    @Provides
    fun provideStateDao(db: AppDatabase) = db.stateDao()

    @Provides
    fun provideUserProgressDao(db: AppDatabase) = db.userProgressDao()
}
