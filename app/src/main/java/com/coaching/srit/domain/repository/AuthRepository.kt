package com.coaching.srit.domain.repository

import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User, AuthError>

    suspend fun signInWithGoogle(): Result<User, AuthError>

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<User, AuthError>

    suspend fun signOut(): Result<Unit, AuthError>

}