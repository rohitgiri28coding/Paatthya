package com.coaching.srit.domain.repository

import com.coaching.srit.domain.FirestoreDbError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun fetchUser(userDocRef: DocumentSnapshot, user: User): Result<User, FirestoreDbError>
    suspend fun registerUser(user: User): Result<User, FirestoreDbError>
    suspend fun updateUserDatabase(user: User): Result<User, FirestoreDbError>
    fun getUser(): Flow<User?>
}