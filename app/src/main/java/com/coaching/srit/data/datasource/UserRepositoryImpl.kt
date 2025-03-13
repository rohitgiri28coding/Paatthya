package com.coaching.srit.data.datasource

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.coaching.srit.domain.FirestoreDbError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.UserRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore, private val context: Context): UserRepository {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
        private val uidKey = stringPreferencesKey("user_uid")
        private val isAdminKey = booleanPreferencesKey("user_isAdmin")
        private val emailKey = stringPreferencesKey("user_email")
    }
    private suspend fun getUserDocReference(user: User): DocumentSnapshot {
        return firestore
            .collection("users")
            .document(user.uid)
            .get()
            .await()
    }

    override suspend fun updateUserDatabase(user: User): Result<User, FirestoreDbError> {
        val userDocRef = getUserDocReference(user)
        return if (!userDocRef.exists()) {
            Log.d("UserDocRef", "User profile does not document exists")
            registerUser(user)
        } else {
            Log.d("UserDocRef", "User profile document exists")
            fetchUser(userDocRef, user)
        }
    }
    override suspend fun fetchUser(userDocRef: DocumentSnapshot, user: User): Result<User, FirestoreDbError> {
        val name = userDocRef.getString("name") ?: "Random One"
        val isAdmin = userDocRef.getBoolean("isAdmin") == true
        saveUser(User(user.uid, name, isAdmin, emptyList(), user.email))
        return Result.Success(User(user.uid, name, isAdmin, emptyList(), user.email))
    }

    override suspend fun registerUser(user: User): Result<User, FirestoreDbError> {
        try {
            firestore.collection("users")
                .document(user.uid)
                .set(
                    hashMapOf(
                        "uid" to user.uid,
                        "isAdmin" to user.isAdmin,
                        "email" to user.email
                    )
                )
                .await()
            saveUser(user)
            return Result.Success(user)
        }catch (e: Exception){
            return Result.Error(FirestoreDbError.DBError.UNKNOWN_ERROR)
        }
    }
    private suspend fun saveUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[uidKey] = user.uid
            preferences[isAdminKey] = user.isAdmin
            preferences[emailKey] = user.email
        }
    }

    override fun getUser(): Flow<User?> {

        return context.dataStore.data.map { preferences ->
            val uid = preferences[uidKey] ?: return@map null
            val isAdmin = preferences[isAdminKey] ?: false
            val email = preferences[emailKey] ?: return@map null
            val user = User(uid=uid, isAdmin=isAdmin, email = email)
            Log.d("user", user.toString())
            user
        }

    }
}