package com.coaching.paatthya.domain.usecase

import com.coaching.paatthya.domain.AuthError
import com.coaching.paatthya.domain.Result
import com.coaching.paatthya.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val emailValidator: EmailValidator
) {
    suspend fun executeForgotPassword(email: String): Result<Unit, AuthError> {
        return when(val emailValidationResult = emailValidator.validateEmail(email)) {
            is Result.Error -> {
                Result.Error(emailValidationResult.error)
            }
            is Result.Success -> {
                authRepository.forgotPassword(email)
                Result.Success(Unit)
            }
        }
    }

}