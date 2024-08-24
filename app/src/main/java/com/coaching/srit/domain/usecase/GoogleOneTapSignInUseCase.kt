package com.coaching.srit.domain.usecase

import com.coaching.srit.domain.Error
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.AuthRepository
import com.coaching.srit.domain.repository.UserRepository
import javax.inject.Inject

class GoogleOneTapSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend fun executeGoogleOneTapSignIn(): Result<User, Error>  {
        return when (val googleSignInResult = authRepository.signInWithGoogle()){
            is Result.Error -> {
                Result.Error(googleSignInResult.error)
            }
            is Result.Success -> {
                when(val userResult = userRepository.updateUserDatabase(googleSignInResult.data)){
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