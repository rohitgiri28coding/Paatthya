package com.coaching.paatthya.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.coaching.paatthya.domain.model.Batches
import com.coaching.paatthya.domain.model.Lecture

sealed class Screen {
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
    data object SavedLectureScreen: Screen()
    data object QuizAndTestsScreen: Screen()
    data object SavedNotesScreen : Screen()
    data class YoutubeLectureScreen (val videoString: String): Screen()
    data class LectureScreen(val lecture: Lecture) : Screen()
    data class DetailBatchScreen(val batch: Batches): Screen()
    data class ExploreBatchScreen(val batch: Batches): Screen()
}

object Router {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.WelcomeScreen)

    fun navigateTo(destination: Screen){
        currentScreen.value = destination
    }
}