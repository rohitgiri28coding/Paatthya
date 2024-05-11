package com.coaching.srit.data.uievent.rules

import android.util.Patterns

object Validator {
    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        )
    }
    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            password.isNotEmpty() && password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[^\\\\s]{8,}\$".toRegex())
        )
    }
}
data class ValidationResult(
    val status: Boolean = false
)