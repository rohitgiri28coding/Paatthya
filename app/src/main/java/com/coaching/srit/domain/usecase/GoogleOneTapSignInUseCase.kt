package com.coaching.srit.domain.usecase

import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.AuthRepository
import javax.inject.Inject

class GoogleOneTapSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun executeGoogleOneTapSignIn(): Result<User, AuthError>  {
        return authRepository.signInWithGoogle()
    }
}