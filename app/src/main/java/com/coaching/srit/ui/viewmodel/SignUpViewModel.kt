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
import com.coaching.srit.domain.usecase.SignUpUseCase
import com.coaching.srit.ui.asUiText
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase, private val googleOneTapSignInUseCase: GoogleOneTapSignInUseCase): ViewModel() {

//    private var TAG = SignUpViewModel::class.simpleName
//    var allValidationPassed = mutableStateOf(false)

    private var signUpUiState = mutableStateOf(AuthUiState())

    var signUpInProgress = mutableStateOf(false)

    private val signUpErrorEventChannel = Channel<UserErrorEvent>()
    val signUpErrorEvents = signUpErrorEventChannel.receiveAsFlow()

    init {
        signUpUiState.value = AuthUiState(email = "", password = "", emailError = false, passwordError = false)
    }
    fun onEvent(event: AuthUiEvent){
        when(event){
            is AuthUiEvent.EmailChange -> {
                signUpUiState.value = signUpUiState.value.copy(
                    email = event.email
                )
//                printState()
            }
            is AuthUiEvent.PasswordChange -> {
                signUpUiState.value = signUpUiState.value.copy(
                    password = event.password
                )
//                printState()
            }

            AuthUiEvent.AuthButtonClicked -> {
                viewModelScope.launch {
                    signUpInProgress.value = true
                    try {
                        val signUpResult = signUpUseCase.executeSignUp(
                            signUpUiState.value.email,
                            signUpUiState.value.password
                        )
                        manageSignUpResult(signUpResult)
                    }catch (e: Exception){
                        Log.d("Error 1: ", "${e.printStackTrace()}")
                        val error = AuthError.SignInError.UNKNOWN_ERROR.asUiText()
                        signUpErrorEventChannel.send(UserErrorEvent.Error(error))
                    }finally {
                        signUpInProgress.value = false
                    }
                }
            }

            AuthUiEvent.GoogleAuthButtonClicked -> {
                viewModelScope.launch {
                    signUpInProgress.value = true
                    try {
                        val googleSignUpResult = googleOneTapSignInUseCase.executeGoogleOneTapSignIn()
                        manageSignUpResult(googleSignUpResult)
                    }catch (e: Exception){
                        Log.d("Error: 2", "${e.message}")
                        val error = AuthError.SignInError.UNKNOWN_ERROR.asUiText()
                        signUpErrorEventChannel.send(UserErrorEvent.Error(error))
                    }finally {
                        signUpInProgress.value = false
                    }
                }
            }
        }
//        validateDataWithRules()
    }

    private suspend fun manageSignUpResult(signUpResult: Result<User, AuthError>) {
        when (signUpResult) {
            is Result.Error -> {
                val error = signUpResult.error.asUiText()
                signUpErrorEventChannel.send(UserErrorEvent.Error(error))
            }
            is Result.Success -> {
                val user = signUpResult.data
                Log.d("user", "$user")
                Router.navigateTo(Screen.HomeScreen)
            }
        }
    }


//    private fun validateDataWithRules() {
//        val emailResult = Validator.validateEmail(
//            signUpUiState.value.email
//        )
//        val passwordResult = Validator.validatePassword(
//            signUpUiState.value.password
//        )
//
//        Log.d(TAG, "inside validate with rules")
//        Log.d(TAG, "email: $emailResult")
//        Log.d(TAG, "password: $passwordResult")
//
//        signUpUiState.value = signUpUiState.value.copy(
//            emailError = emailResult.status,
//            passwordError = passwordResult.status,
//        )
//        allValidationPassed.value = emailResult.status && passwordResult.status
//        Log.d("validation", "${allValidationPassed.value}")
//    }
//
//    private fun printState(){
//        Log.d(TAG, "Inside printState")
//        Log.d(TAG, signUpUiState.value.toString())
//    }


}