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
import com.coaching.paatthya.domain.usecase.SignInUseCase
import com.coaching.paatthya.ui.asUiText
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val googleOneTapSignInUseCase: GoogleOneTapSignInUseCase
): ViewModel(){

    private var signInUiState  = mutableStateOf(AuthUiState())

    var signInInProgress = mutableStateOf(false)
        private set

    private val signInErrorEventChannel = Channel<UserErrorEvent>()
    val signInErrorEvents = signInErrorEventChannel.receiveAsFlow()

    init {
        signInUiState.value = AuthUiState(email = "", password = "")
    }

    fun onEvent(event: AuthUiEvent){
        when(event){
            is AuthUiEvent.EmailChange -> {
                signInUiState.value = signInUiState.value.copy(
                    email = event.email
                )
            }
            is AuthUiEvent.PasswordChange -> {
                signInUiState.value = signInUiState.value.copy(
                    password = event.password
                )
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
            is AuthUiEvent.GoogleAuthButtonClicked -> {
                val context = event.context
                viewModelScope.launch {
                    signInInProgress.value = true
                    try {
                        val googleSignUpResult = googleOneTapSignInUseCase.executeGoogleOneTapSignIn(
                            context
                        )
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
    }
    private suspend fun manageSignInResult(signUpResult: Result<User, Error>) {
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
}