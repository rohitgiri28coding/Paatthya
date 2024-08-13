package com.coaching.srit.data.firebase

import android.util.Log
import com.coaching.srit.domain.model.User

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val user: FirebaseUser) {

    private val firestore = FirebaseFirestore.getInstance()

    private suspend fun getUserDocReference(): DocumentSnapshot? {
        return firestore
            .collection("users")
            .document(user.uid)
            .get()
            .await()
    }

    suspend fun addUserData(name: String, isAdmin: Boolean): User {
        val userDocRef = getUserDocReference()

        return if (userDocRef?.exists() == false) {
            Log.d("UserDocRef", "User profile document exists")
            registerNewUser(name, isAdmin)
        } else {
            Log.d("UserDocRef", "User profile document not exists")
            user(userDocRef)
        }
    }

    private fun user(
        userDocRef: DocumentSnapshot?,
    ): User {
        val name = userDocRef?.getString("name") ?: "Random One"
        val isAdmin = userDocRef?.getBoolean("isAdmin") ?: false
        return User(user.uid, name, isAdmin, user.email ?: "")
    }

    private suspend fun registerNewUser(name: String, isAdmin: Boolean): User {
        firestore.collection("users")
            .document(user.uid)
            .set(hashMapOf(
                "uid" to user.uid,
                "name" to name,
                "isAdmin" to isAdmin,
                "email" to user.email
            ))
            .await()
        return User(user.uid, name, isAdmin, user.email ?: "")
    }
}