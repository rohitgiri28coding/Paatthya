package com.coaching.srit.ui.viewmodel

sealed class ForgotPasswordUiEvent {
    data class EmailChanged(val email: String): ForgotPasswordUiEvent()
    data object ValidateResetPasswordButton: ForgotPasswordUiEvent()
}