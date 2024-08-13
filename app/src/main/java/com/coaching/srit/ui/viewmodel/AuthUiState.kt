package com.coaching.srit.ui.viewmodel

data class AuthUiState(
    var email: String = "",
    var password: String = "",

    var emailError: Boolean = false,
    var passwordError: Boolean = false,
)