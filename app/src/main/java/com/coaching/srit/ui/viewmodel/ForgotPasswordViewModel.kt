package com.coaching.srit.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.UserErrorEvent
import com.coaching.srit.domain.usecase.EmailValidator
import com.coaching.srit.domain.usecase.ForgotPasswordUseCase
import com.coaching.srit.ui.asUiText
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor (private val forgotPasswordUseCase: ForgotPasswordUseCase): ViewModel() {

    private var forgotPasswordUiState = mutableStateOf(ForgotPasswordUiState())

    var isLoading = mutableStateOf(false)

    private val forgotPasswordErrorEventChannel = Channel<UserErrorEvent>()
    val forgotPasswordErrorEvents = forgotPasswordErrorEventChannel.receiveAsFlow()

    fun onEvent(event: ForgotPasswordUiEvent) {
        when(event){
            is ForgotPasswordUiEvent.EmailChanged -> {
                forgotPasswordUiState.value = forgotPasswordUiState.value.copy(
                    email = event.email
                )
            }
            ForgotPasswordUiEvent.ValidateResetPasswordButton -> {
                viewModelScope.launch {
                    isLoading.value = true
                    when(val result = forgotPasswordUseCase.executeForgotPassword(forgotPasswordUiState.value.email)){
                        is Result.Error -> {
                            val error = result.error.asUiText()
                            forgotPasswordErrorEventChannel.send(UserErrorEvent.Error(error))
                        }
                        is Result.Success -> {
                            Router.navigateTo(Screen.ForgotPasswordResetLinkSentScreen)
                        }
                    }
                    isLoading.value = false
                }
            }
        }
    }
}