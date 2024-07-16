package com.example.notemanagerappzen8labs.ui.screen.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notemanagerappzen8labs.common.enum.LoadStatus
import com.example.notemanagerappzen8labs.repositories.Api
import com.example.notemanagerappzen8labs.repositories.MainLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val status: LoadStatus = LoadStatus.Init()
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val log: MainLog?,
    private val api: Api?
):ViewModel(){
    val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun updateUsername(username: String){
        _uiState.value = _uiState.value.copy(username = username)
    }

    fun updatePassword(password: String){
        _uiState.value = _uiState.value.copy(password = password)
    }
    fun reset(){
        _uiState.value = _uiState.value.copy(status = LoadStatus.Loading())
    }

    fun login(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(status = LoadStatus.Loading())
            try {
                var result = api?.login(uiState.value.username, uiState.value.password)
                _uiState.value = _uiState.value.copy(status = LoadStatus.Success())
            }catch (ex: Exception){
                _uiState.value = _uiState.value.copy(status = LoadStatus.Error(ex.message.toString()))
            }
        }
    }

}