package com.cm.schoolcontentmanager.login.states

import androidx.compose.runtime.Immutable

@Immutable
sealed class LoginAction() {
    data class OnFieldChange(val field: FieldType, val value: String) : LoginAction()
    data object SignIn : LoginAction()
    data object SignInWithGoogle : LoginAction()
    data object SignInWithMicrosoft : LoginAction()
    data object ForgotPassword : LoginAction()
}