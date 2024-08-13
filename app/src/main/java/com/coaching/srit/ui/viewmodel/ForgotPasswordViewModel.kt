package com.coaching.srit.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.usecase.EmailValidator
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor (private val emailValidator: EmailValidator): ViewModel() {

    private var TAG = ForgotPasswordViewModel::class.simpleName

    private var forgotPasswordUiState = mutableStateOf(ForgotPasswordUiState())

    var allValidationPassed = mutableStateOf(false)

    var isLoading = mutableStateOf(false)

    fun onEvent(event: ForgotPasswordUiEvent) {
        when(event){
            is ForgotPasswordUiEvent.EmailChanged -> {
                forgotPasswordUiState.value = forgotPasswordUiState.value.copy(
                    email = event.email
                )
                printState()
            }
            ForgotPasswordUiEvent.ValidateResetPasswordButton -> {
                sendLink(forgotPasswordUiState.value.email)
            }
        }
        validateData()
    }

    private fun validateData() {
        when(emailValidator.validateEmail(forgotPasswordUiState.value.email)){
            is Result.Error -> {
                forgotPasswordUiState.value = forgotPasswordUiState.value.copy(
                    emailError = false
                )
                allValidationPassed.value = false
            }
            is Result.Success -> {

                forgotPasswordUiState.value = forgotPasswordUiState.value.copy(
                    emailError = true
                )
                allValidationPassed.value = true
            }
        }



    }

    private fun printState() {
        Log.d(TAG, "Inside printState")
        Log.d(TAG, forgotPasswordUiState.value.toString())
    }

    private fun sendLink(email: String) {
        isLoading.value = true
        printState()
        FirebaseAuth
            .getInstance()
            .sendPasswordResetEmail(email)
            .addOnCompleteListener { task->
                if (task.isSuccessful) {
                    isLoading.value = false
                    Log.d(TAG, "Password reset email sent!")
                    Router.navigateTo(Screen.ForgotPasswordResetLinkSentScreen)
                } else {
                    isLoading.value = false
                    Log.w(TAG, "Password reset email sending failed.", task.exception)
                }
            }
    }
}