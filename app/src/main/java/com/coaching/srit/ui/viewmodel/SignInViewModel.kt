package com.coaching.srit.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coaching.srit.domain.AuthError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.UserErrorEvent
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.usecase.GoogleOneTapSignInUseCase
import com.coaching.srit.domain.usecase.SignInUseCase
import com.coaching.srit.ui.asUiText
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase, private val googleOneTapSignInUseCase: GoogleOneTapSignInUseCase): ViewModel(){

//    private var TAG = SignInViewModel::class.simpleName
//    var allValidationPassed = mutableStateOf(false)
//    var invalidUser = mutableStateOf(false)

    private var signInUiState  = mutableStateOf(AuthUiState())

    var signInInProgress = mutableStateOf(false)

    private val signInErrorEventChannel = Channel<UserErrorEvent>()
    val signInErrorEvents = signInErrorEventChannel.receiveAsFlow()

    init {
        signInUiState.value = AuthUiState(email = "", password = "", emailError = false, passwordError = false)
    }

    fun onEvent(event: AuthUiEvent){
        when(event){
            is AuthUiEvent.EmailChange -> {
                signInUiState.value = signInUiState.value.copy(
                    email = event.email
                )
//                printState()
            }
            is AuthUiEvent.PasswordChange -> {
                signInUiState.value = signInUiState.value.copy(
                    password = event.password
                )
//                printState()
            }

            AuthUiEvent.AuthButtonClicked -> {
                viewModelScope.launch {
                    signInInProgress.value = true
                    try {
                        val signUpResult = signInUseCase.executeSignIn(
                            signInUiState.value.email,
                            signInUiState.value.password
                        )
                        manageSignInResult(signUpResult)
                    }catch (e: Exception){
                        Log.d("Error 1", "${e.message}")
                        val error = AuthError.SignInError.UNKNOWN_ERROR.asUiText()
                        signInErrorEventChannel.send(UserErrorEvent.Error(error))
                    }finally {
                        signInInProgress.value = false
                    }
                }
            }

            AuthUiEvent.GoogleAuthButtonClicked -> {
                viewModelScope.launch {
                    signInInProgress.value = true
                    try {
                        val googleSignUpResult = googleOneTapSignInUseCase.executeGoogleOneTapSignIn()
                        manageSignInResult(googleSignUpResult)
                    }catch (e: Exception){
                        Log.d("Error: ", "${e.message}")
                        val error = AuthError.SignInError.UNKNOWN_ERROR.asUiText()
                        signInErrorEventChannel.send(UserErrorEvent.Error(error))
                    }finally {
                        signInInProgress.value = false
                    }
                }
            }
        }
//        validateDataWithRules()
    }
    private suspend fun manageSignInResult(signUpResult: Result<User, AuthError>) {
        when (signUpResult) {
            is Result.Error -> {
                val error = signUpResult.error.asUiText()
                signInErrorEventChannel.send(UserErrorEvent.Error(error))
            }
            is Result.Success -> {
                val user = signUpResult.data
                Log.d("user", "$user")
                Router.navigateTo(Screen.HomeScreen)
            }
        }
    }

//    fun getEmail(): String {
//        return signInUiState.value.email
//    }
//    fun getPassword(): String {
//        return signInUiState.value.password
//    }
//
//    private fun validateDataWithRules() {
//        val emailResult = Validator.validateEmail(
//            signInUiState.value.email
//        )
//        val passwordResult = Validator.validatePassword(
//            signInUiState.value.password
//        )
//        Log.d(TAG, "inside validate with rules")
//        Log.d(TAG, "email: $emailResult")
//        Log.d(TAG, "password: $passwordResult")
//
//        signInUiState.value = signInUiState.value.copy(
//            emailError = emailResult.status,
//            passwordError = passwordResult.status
//        )
//        allValidationPassed.value = emailResult.status && passwordResult.status
//    }
//
//    private fun printState(){
//        Log.d(TAG, "Inside printState")
//        Log.d(TAG, signInUiState.value.toString())
//    }
}