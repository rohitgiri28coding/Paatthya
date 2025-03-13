package com.coaching.paatthya.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.coaching.paatthya.ui.viewmodel.home.Batches

sealed class Screen {
    data object SplashScreen : Screen()
    data object SignUpScreen : Screen()
    data object LoginScreen : Screen()
    data object ForgotPasswordScreen : Screen()
    data object ForgotPasswordResetLinkSentScreen : Screen()
    data object WelcomeScreen : Screen()
    data object HomeScreen : Screen()
    data object TermsAndConditionsScreen: Screen()
    data object ContactUsScreen: Screen()
    data object ResultScreen: Screen()
    data object GalleryScreen: Screen()
    data object MyBatchScreen: Screen()
    data object MyDoubtsScreen: Screen()
    data object MyDownloadsScreen: Screen()
    data object NotesScreen : Screen()
    data object LectureScreen : Screen()

    data class ExploreBatchScreen(val batch: Batches): Screen()
}

object Router {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.WelcomeScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}