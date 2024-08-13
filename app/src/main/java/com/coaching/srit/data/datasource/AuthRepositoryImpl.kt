package com.coaching.srit.data.datasource

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.coaching.srit.data.core.Constants
import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.AuthRepository
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val context: Context
): AuthRepository {

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User, AuthError> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(User(uid = auth.uid!!, email = auth.currentUser!!.email!!, name = ""))
        }catch (e: Exception) {
            Log.d("sign in error2: ", "${e.printStackTrace()}")
            if (e.message?.contains("INVALID_LOGIN_CREDENTIALS") == true)
                Result.Error(AuthError.SignInError.INVALID_CREDENTIALS)
            else if (e.message?.contains("EMAIL_NOT_FOUND") == true)
                Result.Error(AuthError.SignInError.INVALID_CREDENTIALS)
            else if (e.message?.contains("NO_INTERNET") == true)
                Result.Error(AuthError.SignInError.NO_INTERNET)
            else
                Result.Error(AuthError.SignInError.UNKNOWN_ERROR)
        }
    }

    override suspend fun signInWithGoogle(): Result<User, AuthError> {
        try {
            val credentialManager = CredentialManager.create(context)

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setAutoSelectEnabled(true)
                .setServerClientId(Constants.WEB_CLIENT_ID)
                .build()
            val result = credentialManager.getCredential(
                context = context,
                request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()
            )
            val credential = result.credential
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val googleIdToken = googleIdTokenCredential.idToken
            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

            auth.signInWithCredential(firebaseCredential).addOnSuccessListener {  }.await()
            return Result.Success(User(uid = auth.uid!!, email = auth.currentUser!!.email!!, name = auth.currentUser!!.displayName!!))
        }catch (e: Exception){
            Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
            return Result.Error(AuthError.SignInError.UNKNOWN_ERROR)
        }
    }

    override suspend fun signUpWithEmailAndPassword(email: String, password: String): Result<User, AuthError> {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Log.d("sign up: ", "$auth")
             return Result.Success(User(uid = auth.uid!!, email = auth.currentUser!!.email!!, name = ""))
        }catch (e: Exception){
            Log.d("Error 6: ", "${e.printStackTrace()}")
            return Result.Error(AuthError.SignInError.UNKNOWN_ERROR)
        }
    }

    override suspend fun signOut(): Result<Unit, AuthError> {
        try {
            val credentialManager = CredentialManager.create(context)
            auth.signOut()
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            return Result.Success(Unit)
        }catch (e: Exception){
            return Result.Error(AuthError.SignInError.UNKNOWN_ERROR)
        }
    }

}