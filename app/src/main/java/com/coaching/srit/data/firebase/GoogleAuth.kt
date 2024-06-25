package com.coaching.srit.data.firebase

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.ClearCredentialException
import com.coaching.srit.data.core.Constants
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

/*TODO: On Android 14 or higher, you can reduce latency when showing
   the account selector by using the prepareGetCredential() method
   before calling getCredential().*/

class GoogleAuth(private val context: Context, private val auth: FirebaseAuth) {

    private val credentialManager = CredentialManager.create(context)

    private val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setAutoSelectEnabled(true)
        .setServerClientId(Constants.WEB_CLIENT_ID)
        .build()

    private fun fetchCredentialRequest(): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    suspend fun authenticateWithGoogle(): FirebaseUser?{
        try {
            val result = credentialManager.getCredential(
                context = context,
                request = fetchCredentialRequest()
            )
            val credential = result.credential
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val googleIdToken = googleIdTokenCredential.idToken
            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
            val authResult = auth.signInWithCredential(firebaseCredential).await()
            return authResult.user
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
        return null
    }

    suspend fun signOutUser(): FirebaseUser? {
        return try {
            auth.signOut()
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            auth.currentUser // Return the current user (which should be null after sign-out)
        } catch (e: ClearCredentialException) {
            // Handle exceptions during sign-out
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.d("ClearCredentialException", "Error: ${e.message}")
            auth.currentUser // Return the current user (might still be non-null if sign-out failed)
        }
    }
}