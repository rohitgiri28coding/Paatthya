package com.coaching.paatthya.domain.usecase

import com.coaching.paatthya.domain.repository.AuthError
import com.coaching.paatthya.domain.repository.Result
import com.coaching.paatthya.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun executeSignOut(): Result<Unit, AuthError> {
        return authRepository.signOut()
    }
}