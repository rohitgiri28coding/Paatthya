package com.coaching.paatthya.domain.usecase

import com.coaching.paatthya.domain.repository.AuthError
import com.coaching.paatthya.domain.repository.Result

class PasswordValidator {
    fun validatePassword(password: String): Result<Unit, AuthError.PasswordValidationError>{
        if (password.length < 9) {
            return Result.Error(AuthError.PasswordValidationError.TOO_SHORT)
        }

        val hasUppercaseChar = password.any { it.isUpperCase() }
        if (!hasUppercaseChar) {
            return Result.Error(AuthError.PasswordValidationError.NO_UPPERCASE)
        }

        val hasLowercaseChar = password.any { it.isLowerCase() }
        if (!hasLowercaseChar) {
            return Result.Error(AuthError.PasswordValidationError.NO_LOWERCASE)
        }

        val hasDigit = password.any { it.isDigit() }
        if (!hasDigit) {
            return Result.Error(AuthError.PasswordValidationError.NO_DIGIT)
        }

//        val hasSpecialChar = password.any{ password.matches(Regex("[^a-zA-Z0-9\\\\s]")) }
//        if (!hasSpecialChar) {
//            return Result.Error(AuthError.PasswordValidationError.NO_SPECIAL_CHARACTER)
//        }

        return Result.Success(Unit)
    }
}