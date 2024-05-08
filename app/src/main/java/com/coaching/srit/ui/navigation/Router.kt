package com.coaching.srit.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    data object SignUpScreen: Screen()
    data object TermsAndConditionsScreen: Screen()
    data object LoginScreen: Screen()
    data object ForgotPasswordScreen: Screen()
    data object ForgotPasswordResetLinkSentScreen: Screen()
    data object HomeScreen: Screen()
}
object Router {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}