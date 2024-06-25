package com.coaching.srit.ui.screens.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coaching.srit.data.uievent.login.LoginUiEvent
import com.coaching.srit.data.uievent.rules.Validator

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
//            LoginUiEvent.ValidateLoginButtonClicked -> {
////                login()
//            }
        }
        validateDataWithRules()
    }
    fun getEmail(): String {
        return loginUiState.value.email
    }
    fun getPassword(): String {
        return loginUiState.value.password
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

//    fun login(auth: FirebaseAuth, onSignedIn: (FirebaseUser) -> Unit) {
//        Log.d(TAG, "Inside login validation")
//        printState()
//        loginUserInFirebase(auth = auth, email = loginUiState.value.email, password = loginUiState.value.password){
//            onSignedIn(it)
//        }
//    }
//    private fun loginUserInFirebase(auth: FirebaseAuth, email: String, password: String, onSignedIn: (FirebaseUser) -> Unit){
//        loginInProgress.value = true
//        auth
//            .signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                loginInProgress.value = false
//                if (task.isSuccessful) {
//                    Log.d(TAG, "Login successful!")
//                    auth.currentUser?.let { onSignedIn(it) }
//                } else {
//                    Log.w(TAG, "Login failed.", task.exception)
//                }
//            }
//            .addOnFailureListener{
//                loginInProgress.value = false
//                Log.w(TAG, "Login failed -> ${it.message}")
//                invalidUser.value = true
//            }
//    }
}