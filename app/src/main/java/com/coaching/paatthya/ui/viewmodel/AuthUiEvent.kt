package com.coaching.paatthya.ui.viewmodel

import android.content.Context

sealed class AuthUiEvent {
    data class EmailChange(val email: String): AuthUiEvent()
    data class PasswordChange(val password: String): AuthUiEvent()
    data object AuthButtonClicked: AuthUiEvent()
    data class GoogleAuthButtonClicked(val context: Context): AuthUiEvent()
}