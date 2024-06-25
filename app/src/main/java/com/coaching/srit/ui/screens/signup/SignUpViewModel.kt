package com.coaching.srit.ui.screens.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coaching.srit.data.uievent.rules.Validator
import com.coaching.srit.data.uievent.signup.SignUpUiEvent

class SignUpViewModel: ViewModel() {

    private var TAG = SignUpViewModel::class.simpleName

    private var registrationUiState = mutableStateOf(SignUpUiState())

    var allValidationPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignUpUiEvent){
        when(event){
            is SignUpUiEvent.EmailChange -> {
                registrationUiState.value = registrationUiState.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignUpUiEvent.PasswordChange -> {
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )
                printState()
            }
//            is SignUpUiEvent.RegisterButtonClicked -> {
//
//            }
        }
        validateDataWithRules()
    }

    fun getEmail(): String {
        return registrationUiState.value.email
    }
    fun getPassword(): String {
        return registrationUiState.value.password
    }
    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(
            registrationUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            registrationUiState.value.password
        )

        Log.d(TAG, "inside validate with rules")
        Log.d(TAG, "email: $emailResult")
        Log.d(TAG, "password: $passwordResult")

        registrationUiState.value = registrationUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )
        allValidationPassed.value = emailResult.status && passwordResult.status
        Log.d("validation", "${allValidationPassed.value}")
    }

    private fun printState(){
        Log.d(TAG, "Inside printState")
        Log.d(TAG, registrationUiState.value.toString())
    }
//    fun signUp(auth: FirebaseAuth, onSignedIn: (FirebaseUser) -> Unit) {
//        Log.d(TAG, "Inside signup")
//        printState()
//
//        createUserInFirebase(
//            auth = auth,
//            email = registrationUiState.value.email,
//            password = registrationUiState.value.password
//        ){
//            onSignedIn(it)
//        }
//    }
//    private fun createUserInFirebase(auth: FirebaseAuth, email: String, password: String, onSignedIn: (FirebaseUser) -> Unit){
//        signUpInProgress.value = true
//        auth
//            .createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                Log.d(TAG, "Inside on complete listener")
//                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
//                signUpInProgress.value = false
//                if (it.isSuccessful) {
//                    auth.currentUser?.let { it1 -> onSignedIn(it1) }
//                }
//            }
//            .addOnFailureListener{
//                signUpInProgress.value = false
//                Log.d(TAG, "Inside on failure listener")
//                Log.d(TAG, "Exception -> ${it.message}")
//                Log.d(TAG, "Exception -> ${it.printStackTrace()}")
//            }
//    }
}