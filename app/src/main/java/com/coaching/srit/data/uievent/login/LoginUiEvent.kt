package com.coaching.srit.data.uievent.login

sealed class LoginUiEvent {
    data class EmailChange(val email: String): LoginUiEvent()
    data class PasswordChange(val password: String): LoginUiEvent()
//    data object ValidateLoginButtonClicked: LoginUiEvent()
}