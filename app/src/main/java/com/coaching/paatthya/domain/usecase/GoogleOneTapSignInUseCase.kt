package com.coaching.paatthya.domain.usecase

import android.content.Context
import com.coaching.paatthya.domain.repository.Error
import com.coaching.paatthya.domain.repository.Result
import com.coaching.paatthya.domain.model.User
import com.coaching.paatthya.domain.repository.AuthRepository
import com.coaching.paatthya.domain.repository.UserRepository
import javax.inject.Inject

class GoogleOneTapSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) {
    suspend fun executeGoogleOneTapSignIn(context: Context): Result<User, Error>  {
        return when (val googleSignInResult = authRepository.signInWithGoogle(context)){
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