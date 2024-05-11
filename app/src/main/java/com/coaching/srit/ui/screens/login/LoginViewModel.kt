package com.coaching.srit.ui.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coaching.srit.data.uievent.login.LoginUiEvent
import com.coaching.srit.data.uievent.rules.Validator
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel(){

    private var TAG = LoginViewModel::class.simpleName

    private var loginUiState  = mutableStateOf(LoginUiState())

    var allValidationPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    var invalidUser = mutableStateOf(false)

    fun onEvent(event: LoginUiEvent){
        when(event){
            is LoginUiEvent.EmailChange -> {
                loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )
                printState()
            }
            is LoginUiEvent.PasswordChange -> {
                loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
                printState()
            }
            LoginUiEvent.ValidateLoginButtonClicked -> {
                login()
            }
        }
        validateDataWithRules()
    }

    private fun login() {
        Log.d(TAG, "Inside login validation")
        printState()
        loginUserInFirebase(email = loginUiState.value.email, password = loginUiState.value.password)
    }

    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(
            loginUiState.value.email
        )
        val passwordResult = Validator.validatePassword(
            loginUiState.value.password
        )
        Log.d(TAG, "inside validate with rules")
        Log.d(TAG, "email: $emailResult")
        Log.d(TAG, "password: $passwordResult")

        loginUiState.value = loginUiState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        allValidationPassed.value = emailResult.status && passwordResult.status
    }

    private fun printState(){
        Log.d(TAG, "Inside printState")
        Log.d(TAG, loginUiState.value.toString())
    }
    private fun loginUserInFirebase(email: String, password: String){
        loginInProgress.value = true
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                loginInProgress.value = false
                if (task.isSuccessful) {
                    Log.d(TAG, "Login successful!")
                    Router.navigateTo(Screen.HomeScreen)
                } else {
                    Log.w(TAG, "Login failed.", task.exception)
                    // Handle login failure (e.g., display error message to user)
                }
            }
            .addOnFailureListener{
                loginInProgress.value = false
                Log.w(TAG, "Login failed -> ${it.message}")
                invalidUser.value = true
            }
    }
}