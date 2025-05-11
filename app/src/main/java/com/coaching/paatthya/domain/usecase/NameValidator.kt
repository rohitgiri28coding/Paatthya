package com.coaching.paatthya.domain.usecase

import com.coaching.paatthya.domain.repository.AuthError
import com.coaching.paatthya.domain.repository.Result

class NameValidator {
    fun validateName(name: String): Result<Unit, AuthError.NameValidationError>{
        if (name.isEmpty() || name.isBlank()) {
            return Result.Error(AuthError.NameValidationError.NAME_EMPTY)
        }else if (name.length < 3) {
            return Result.Error(AuthError.NameValidationError.NAME_INVALID)
        }
        return Result.Success(Unit)

    }
}