package com.coaching.paatthya.domain.usecase

import com.coaching.paatthya.domain.logger.Logger
import com.coaching.paatthya.domain.model.User
import com.coaching.paatthya.domain.repository.AuthRepository
import com.coaching.paatthya.domain.repository.Error
import com.coaching.paatthya.domain.repository.Result
import com.coaching.paatthya.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val authValidator: AuthDataValidation,
    private val userRepository: UserRepository,
    private val logger: Logger
) {
    suspend fun executeSignUp(
        email: String,
        password: String,
    ): Result<User, Error> {
        return when (val emailValidationResult = authValidator.validateName(email)) {
            is Result.Error -> {
                logger.log("SignUp failed: Invalid email - $emailValidationResult")
                Result.Error(emailValidationResult.error)
            }

            is Result.Success -> {
                when (val passwordValidationResult = authValidator.validatePassword(password)) {
                    is Result.Error -> {
                        logger.log("SignUp failed: Invalid password - $passwordValidationResult")
                        Result.Error(passwordValidationResult.error)
                    }

                    is Result.Success -> {
                        logger.log("Attempting sign-up with email: $email")
                        when (val signUpResult = authRepository.signUpWithEmailAndPassword(email, password)) {
                            is Result.Error -> {
                                logger.log("SignUp failed during Firebase sign-up - ${signUpResult.error}")
                                Result.Error(signUpResult.error)
                            }

                            is Result.Success -> {
                                val user = User(
                                    uid = signUpResult.data.uid,
                                    email = signUpResult.data.email
                                )
                                when (val userResult = userRepository.updateUserDatabase(user)) {
                                    is Result.Error -> {
                                        authRepository.signOut()
                                        logger.log("SignUp failed during Firestore update - ${userResult.error}")
                                        Result.Error(userResult.error)
                                    }

                                    is Result.Success -> {
                                        logger.log("SignUp successful for user: ${user.email}")
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
