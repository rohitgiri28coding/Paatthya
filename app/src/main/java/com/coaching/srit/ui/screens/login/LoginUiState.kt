package com.coaching.srit.ui.screens.login

data class LoginUiState (
    var email: String = "",
    var password: String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false,
)