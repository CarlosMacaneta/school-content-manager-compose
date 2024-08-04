package com.cm.schoolcontentmanager.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.schoolcontentmanager.login.states.FieldType
import com.cm.schoolcontentmanager.login.states.LoginAction
import com.cm.schoolcontentmanager.login.states.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.SignIn -> { signIn() }
            is LoginAction.SignInWithGoogle -> { signInWithGoogle() }
            is LoginAction.SignInWithMicrosoft -> {}
            is LoginAction.ForgotPassword -> {}
            is LoginAction.OnFieldChange -> {
                updateFieldData(action.field, action.value)
            }
        }
    }

    private fun updateFieldData(field: FieldType, value: String) {
        when (field) {
            FieldType.EMAIL -> {
                _uiState.update {
                    it.copy(email = value)
                }
            }
            FieldType.PASSWORD -> {
                _uiState.update {
                    it.copy(password = value)
                }
            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
        }
    }

    private fun signInWithGoogle() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
        }
    }
}