package com.coaching.srit.domain.usecase

import android.util.Log
import com.coaching.srit.domain.Error
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.AuthRepository
import com.coaching.srit.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val authValidator: AuthDataValidation,
    private val userRepository: UserRepository
) {
    suspend fun executeSignIn(email: String, password: String): Result<User, Error> {
        return when (val emailValidationResult = authValidator.validateEmail(email)) {
            is Result.Error -> {
                Result.Error(emailValidationResult.error)
            }
            is Result.Success -> {
                when (val passwordValidationResult = authValidator.validatePassword(password)) {
                    is Result.Error -> {
                        Result.Error(passwordValidationResult.error)
                    }
                    is Result.Success -> {
                        Log.d("executing sign in", "$Result")
                        when (val signInResult =
                            authRepository.signInWithEmailAndPassword(email, password)) {
                            is Result.Error -> {
                                Result.Error(signInResult.error)
                            }

                            is Result.Success -> {
                                val user = User(
                                    uid = signInResult.data.uid,
                                    email = signInResult.data.email
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