package com.coaching.srit.domain.usecase

import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun executeSignOut(): Result<Unit, AuthError> {
        return authRepository.signOut()
    }
}