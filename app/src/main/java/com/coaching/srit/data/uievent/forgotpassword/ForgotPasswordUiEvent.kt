package com.coaching.srit.data.uievent.forgotpassword

sealed class ForgotPasswordUiEvent {
    data class EmailChanged(val email: String): ForgotPasswordUiEvent()
    data object ValidateResetPasswordButton: ForgotPasswordUiEvent()
}