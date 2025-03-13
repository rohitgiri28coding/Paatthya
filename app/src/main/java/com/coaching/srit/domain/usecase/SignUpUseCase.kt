package com.coaching.srit.domain.usecase

import android.util.Log
import com.coaching.srit.domain.Error
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.AuthRepository
import com.coaching.srit.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val authValidator: AuthDataValidation,
    private val userRepository: UserRepository
) {
    suspend fun executeSignUp(
        email: String,
        password: String,
    ): Result<User, Error> {
        return when (val emailValidationResult = authValidator.validateName(email)) {
            is Result.Error -> {
                Result.Error(emailValidationResult.error)
            }

            is Result.Success -> {
                when (val passwordValidationResult =
                    authValidator.validatePassword(password)) {
                    is Result.Error -> {
                        Result.Error(passwordValidationResult.error)
                    }

                    is Result.Success -> {
                        Log.d("executing sign up", "$Result")
                        when (val signUpResult =
                            authRepository.signUpWithEmailAndPassword(email, password)) {
                            is Result.Error -> {
                                Result.Error(signUpResult.error)
                            }

                            is Result.Success -> {
                                val user = User(
                                    uid = signUpResult.data.uid,
                                    email = signUpResult.data.email
                                )
                                when (val userResult =
                                    userRepository.updateUserDatabase(user)) {
                                    is Result.Error -> {
                                        authRepository.signOut()
                                        Result.Error(userResult.error)
                                    }

                                    is Result.Success -> {
                                        Result.Success(userResult.data)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}