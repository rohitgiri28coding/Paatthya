package com.coaching.paatthya.ui.viewmodel

sealed class ForgotPasswordUiEvent {
    data class EmailChanged(val email: String): ForgotPasswordUiEvent()
    data object ValidateResetPasswordButton: ForgotPasswordUiEvent()
}