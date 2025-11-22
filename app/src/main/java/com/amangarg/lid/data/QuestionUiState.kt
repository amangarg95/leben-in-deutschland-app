package com.amangarg.lid.data

import com.amangarg.lid.data.local.model.Question
import com.amangarg.lid.data.local.model.Translation

sealed class QuestionUiState {
    object Loading : QuestionUiState()
    data class Success(val question: Question, val translations: List<Translation>) : QuestionUiState()
    data class Error(val message: String, val throwable: Throwable? = null) : QuestionUiState()
}
