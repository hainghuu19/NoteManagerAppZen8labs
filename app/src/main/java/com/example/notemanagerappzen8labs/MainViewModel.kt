package com.example.notemanagerappzen8labs

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class MainState(
    val error: String = "",
)

class MainViewModel @Inject constructor(): ViewModel() {
    val _uiState = MutableStateFlow(MainState())
    val uiState = _uiState.asStateFlow()

    fun setError(message : String){
        _uiState.value = _uiState.value.copy(error = message)
    }
}