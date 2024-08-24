package com.coaching.srit.domain.usecase

import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.repository.AuthRepository
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