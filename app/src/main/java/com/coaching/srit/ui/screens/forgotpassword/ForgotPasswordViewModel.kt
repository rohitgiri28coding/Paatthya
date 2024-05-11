package com.coaching.srit.ui.screens.forgotpassword

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coaching.srit.data.uievent.forgotpassword.ForgotPasswordUiEvent
import com.coaching.srit.data.uievent.rules.Validator
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordViewModel: ViewModel() {
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
        val emailResult = Validator.validateEmail(
            forgotPasswordUiState.value.email
        )
        Log.d(TAG, "inside validate with rules")
        Log.d(TAG, "email: $emailResult")

        forgotPasswordUiState.value = forgotPasswordUiState.value.copy(
            emailError = emailResult.status
        )
        allValidationPassed.value = emailResult.status
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