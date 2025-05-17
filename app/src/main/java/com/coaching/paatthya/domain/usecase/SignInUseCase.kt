package com.coaching.paatthya.domain.usecase

import com.coaching.paatthya.domain.logger.Logger
import com.coaching.paatthya.domain.model.User
import com.coaching.paatthya.domain.repository.AuthRepository
import com.coaching.paatthya.domain.repository.Error
import com.coaching.paatthya.domain.repository.Result
import com.coaching.paatthya.domain.repository.UserRepository
import javax.inject.Inject

// domain/usecase/SignInUseCase.kt

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val authValidator: AuthDataValidation,
    private val userRepository: UserRepository,
    private val logger: Logger
) {
    suspend fun executeSignIn(email: String, password: String): Result<User, Error> {
        logger.log("Validating email")
        val emailResult = authValidator.validateEmail(email)
        if (emailResult is Result.Error) {
            logger.error("Invalid email: $email")
            return Result.Error(emailResult.error)
        }

        logger.log("Validating password")
        val passwordResult = authValidator.validatePassword(password)
        if (passwordResult is Result.Error) {
            logger.error("Invalid password")
            return Result.Error(passwordResult.error)
        }

        logger.log("Signing in with Firebase")
        val signInResult = authRepository.signInWithEmailAndPassword(email, password)
        if (signInResult is Result.Error) {
            logger.error("Sign-in failed: ${signInResult.error}")
            return signInResult
        }

        val user = (signInResult as Result.Success).data

        logger.log("Updating user in Firestore")
        val updateResult = userRepository.updateUserDatabase(user)
        if (updateResult is Result.Error) {
            logger.error("Failed to update Firestore: ${updateResult.error}")
            authRepository.signOut()
            return Result.Error(updateResult.error)
        }

        logger.log("Sign-in successful")
        return Result.Success(user)
    }
}
