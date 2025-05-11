package com.coaching.paatthya.domain.usecase

import android.util.Patterns
import com.coaching.paatthya.domain.repository.AuthError
import com.coaching.paatthya.domain.repository.Result

class EmailValidator {
    fun validateEmail(email: String): Result<Unit, AuthError.EmailValidationError> {
        if (email.isEmpty() || email.isBlank()) {
            return Result.Error(AuthError.EmailValidationError.EMAIL_EMPTY)
        }
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return Result.Success(Unit)
        }
        return Result.Error(AuthError.EmailValidationError.EMAIL_INVALID)
    }
}