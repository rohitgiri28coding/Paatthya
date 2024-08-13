package com.coaching.srit.domain.usecase

import android.util.Log
import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val authValidator: AuthDataValidation
) {
    suspend fun executeSignIn(email: String, password: String): Result<User, AuthError> {
        return when(val emailValidationResult = authValidator.validateEmail(email)){
            is Result.Error -> {
                Result.Error(emailValidationResult.error)
            }
            is Result.Success -> {
                when(val passwordValidationResult = authValidator.validatePassword(password)){
                    is Result.Error -> {
                        Result.Error(passwordValidationResult.error)
                    }
                    is Result.Success -> {
                        Log.d("executing sign in", "$Result")
                        authRepository.signInWithEmailAndPassword(email, password)
                    }
                }
            }
        }
    }
}