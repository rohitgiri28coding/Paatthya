package com.coaching.paatthya.domain.repository

import android.content.Context
import com.coaching.paatthya.domain.AuthError
import com.coaching.paatthya.domain.Result
import com.coaching.paatthya.domain.model.User

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User, AuthError>

    suspend fun signInWithGoogle(context: Context): Result<User, AuthError>

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<User, AuthError>

    suspend fun signOut(): Result<Unit, AuthError>

    suspend fun forgotPassword(email: String): Result<Unit, AuthError>
}