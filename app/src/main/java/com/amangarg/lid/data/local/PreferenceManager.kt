package com.amangarg.lid.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "lid_settings")

class PreferenceManager @Inject constructor(private val context: Context) {

    companion object {
        val LAST_QUESTION_ID = stringPreferencesKey("last_question_id")
    }

    suspend fun saveLastQuestionId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[LAST_QUESTION_ID] = id
        }
    }

    fun getLastQuestionId(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[LAST_QUESTION_ID]
        }
    }
}
