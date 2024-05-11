package com.coaching.srit.ui.screens.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coaching.srit.data.uievent.rules.Validator
import com.coaching.srit.data.uievent.signup.SignUpUiEvent
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

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
            is SignUpUiEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d(TAG, "Inside signup")
        printState()

        createUserInFirebase(
            email = registrationUiState.value.email,
            password = registrationUiState.value.password
        )
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

    private fun createUserInFirebase(email: String, password: String){
        signUpInProgress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside on complete listener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                signUpInProgress.value = false
                if (it.isSuccessful) {
                    Router.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener{
                signUpInProgress.value = false
                Log.d(TAG, "Inside on failure listener")
                Log.d(TAG, "Exception -> ${it.message}")
                Log.d(TAG, "Exception -> ${it.printStackTrace()}")
            }
    }
}