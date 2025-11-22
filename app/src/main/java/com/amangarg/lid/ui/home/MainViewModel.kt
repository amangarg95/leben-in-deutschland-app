package com.amangarg.lid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amangarg.lid.data.QuestionUiState
import com.amangarg.lid.domain.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuestionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<QuestionUiState>(QuestionUiState.Loading)
    val uiState: StateFlow<QuestionUiState> = _uiState.asStateFlow()

    init {
        importQuestions()
    }

    private fun importQuestions() {
        viewModelScope.launch {
            repository.importFromAsset("question_en_only.json")
            loadInitialQuestion()
        }
    }

    private fun loadInitialQuestion() {
        viewModelScope.launch {
            val lastId = repository.getLastQuestionId().firstOrNull()
            if (lastId != null) {
                loadQuestion(lastId)
            } else {
                // Load first question if no last ID saved
                val firstQuestion = repository.getQuestion().firstOrNull()
                if (firstQuestion != null) {
                    loadQuestion(firstQuestion.id)
                } else {
                    _uiState.value = QuestionUiState.Error("No questions found")
                }
            }
        }
    }

    fun loadQuestion(questionId: String) {
        viewModelScope.launch {
            _uiState.value = QuestionUiState.Loading

            val question = repository.getQuestionEntity(questionId)
            if (question != null) {
                // Fetch translations
                val translations = repository.getQuestionById(questionId).firstOrNull() ?: emptyList()

                _uiState.value = QuestionUiState.Success(
                    question = question,
                    translations = translations
                )
                saveLastQuestionId(questionId)
            } else {
                _uiState.value = QuestionUiState.Error("Question not found")
            }
        }
    }

    private fun saveLastQuestionId(id: String) {
        viewModelScope.launch {
            repository.saveLastQuestionId(id)
        }
    }
}
