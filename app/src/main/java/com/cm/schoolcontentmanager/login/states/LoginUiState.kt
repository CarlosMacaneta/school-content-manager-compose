package com.cm.schoolcontentmanager.login.states

import android.util.Patterns
import androidx.compose.runtime.Immutable
import com.cm.schoolcontentmanager.utils.Util

@Immutable
data class LoginUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = ""
) {
    val isSignInEnabled: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && isValidEmail && isValidPassword

    val isValidEmail: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank()

    val isValidPassword: Boolean
        get() = Util.isValidPassword(password) && password.isNotBlank()
}
