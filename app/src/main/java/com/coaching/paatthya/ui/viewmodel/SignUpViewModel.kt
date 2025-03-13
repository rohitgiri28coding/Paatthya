package com.coaching.paatthya.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coaching.paatthya.domain.AuthError
import com.coaching.paatthya.domain.Error
import com.coaching.paatthya.domain.Result
import com.coaching.paatthya.domain.UserErrorEvent
import com.coaching.paatthya.domain.model.User
import com.coaching.paatthya.domain.usecase.GoogleOneTapSignInUseCase
import com.coaching.paatthya.domain.usecase.SignUpUseCase
import com.coaching.paatthya.ui.asUiText
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase, private val googleOneTapSignInUseCase: GoogleOneTapSignInUseCase): ViewModel() {

    private var signUpUiState = mutableStateOf(AuthUiState())

    var signUpInProgress = mutableStateOf(false)
        private set

    private val signUpErrorEventChannel = Channel<UserErrorEvent>()
    val signUpErrorEvents = signUpErrorEventChannel.receiveAsFlow()

    init {
        signUpUiState.value = AuthUiState(email = "", password = "")
    }
    fun onEvent(event: AuthUiEvent){
        when(event){
            is AuthUiEvent.EmailChange -> {
                signUpUiState.value = signUpUiState.value.copy(
                    email = event.email
                )
            }
            is AuthUiEvent.PasswordChange -> {
                signUpUiState.value = signUpUiState.value.copy(
                    password = event.password
                )
            }

            AuthUiEvent.AuthButtonClicked -> {
                viewModelScope.launch {
                    signUpInProgress.value = true
                    try {
                        val signUpResult = signUpUseCase.executeSignUp(
                            signUpUiState.value.email,
                            signUpUiState.value.password,
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

            is AuthUiEvent.GoogleAuthButtonClicked -> {
                val context = event.context
                viewModelScope.launch {
                    signUpInProgress.value = true
                    try {
                        val googleSignUpResult = googleOneTapSignInUseCase.executeGoogleOneTapSignIn(context)
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
    }

    private suspend fun manageSignUpResult(signUpResult: Result<User, Error>) {
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
}