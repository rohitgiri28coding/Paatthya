package com.coaching.srit.domain.usecase

import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result
import javax.inject.Inject

class AuthDataValidation @Inject constructor(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
)  {
    fun validateEmail(email: String): Result<Unit, AuthError> {
        return emailValidator.validateEmail(email)
    }
    fun validatePassword(password: String): Result<Unit, AuthError> {
        return passwordValidator.validatePassword(password)
    }
}