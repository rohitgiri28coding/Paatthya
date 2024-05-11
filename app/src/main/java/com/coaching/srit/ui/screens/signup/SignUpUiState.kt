package com.coaching.srit.ui.screens.signup

data class SignUpUiState(
    var email: String = "",
    var password: String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false
)