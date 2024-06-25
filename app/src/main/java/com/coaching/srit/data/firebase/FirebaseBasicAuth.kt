package com.coaching.srit.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseBasicAuth(private val auth: FirebaseAuth) {

    suspend fun signIn(email: String, password: String): FirebaseUser? {
        auth
            .signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d("FirebaseBasicAuth", "signInWithEmail:success")
            }
            .addOnFailureListener{
                Log.d("FirebaseBasicAuth", "signInWithEmail:failure")
            }
            .await()
        Log.d("user", "${auth.currentUser}")
        return auth.currentUser
    }

    suspend fun signUp(email: String, password: String): FirebaseUser?{
        auth
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d("FirebaseBasicAuth", "signUpWithEmail:success")
            }
            .addOnFailureListener{
                Log.d("FirebaseBasicAuth", "signUpWithEmail:failure")
            }
            .await()
        Log.d("user", "${auth.currentUser}")
        return auth.currentUser
    }
}