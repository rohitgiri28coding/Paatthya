package com.coaching.srit.domain.usecase

import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result

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