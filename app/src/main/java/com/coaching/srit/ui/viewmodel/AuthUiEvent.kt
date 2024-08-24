package com.coaching.srit.ui.viewmodel

sealed class AuthUiEvent {
    data class NameChange(val name: String): AuthUiEvent()
    data class EmailChange(val email: String): AuthUiEvent()
    data class PasswordChange(val password: String): AuthUiEvent()
    data object AuthButtonClicked: AuthUiEvent()
    data object GoogleAuthButtonClicked: AuthUiEvent()
}