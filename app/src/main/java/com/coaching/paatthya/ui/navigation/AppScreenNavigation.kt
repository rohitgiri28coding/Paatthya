package com.coaching.paatthya.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.coaching.paatthya.ui.screens.auth.forgotpassword.ForgotPasswordScreen
import com.coaching.paatthya.ui.screens.auth.forgotpassword.ForgotPasswordScreenResetLinkSent
import com.coaching.paatthya.ui.screens.auth.login.LoginScreen
import com.coaching.paatthya.ui.screens.auth.signup.SignUpScreen
import com.coaching.paatthya.ui.screens.auth.splashscreen.SplashScreen
import com.coaching.paatthya.ui.screens.auth.welcomescreen.WelcomeScreen
import com.coaching.paatthya.ui.screens.home.Home
import com.coaching.paatthya.ui.screens.home.batches.BatchDetailScreen
import com.coaching.paatthya.ui.screens.home.batches.ExploreBatchScreen
import com.coaching.paatthya.ui.screens.home.contactus.ContactUsScreen
import com.coaching.paatthya.ui.screens.home.gallery.GalleryScreen
import com.coaching.paatthya.ui.screens.home.my_learning.MyBatchesScreen
import com.coaching.paatthya.ui.screens.home.my_learning.SavedLectureScreen
import com.coaching.paatthya.ui.screens.home.my_learning.QuizAndTestsScreen
import com.coaching.paatthya.ui.screens.home.my_learning.SavedNotesScreen
import com.coaching.paatthya.ui.screens.ytplayer.YoutuberLectureScreen
import com.coaching.paatthya.ui.screens.home.result.ResultScreen
import com.coaching.paatthya.ui.screens.home.study.LectureScreen
import com.coaching.paatthya.ui.screens.home.termsandconditions.TermsAndConditionsScreen
import com.coaching.paatthya.ui.viewmodel.AuthViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AppScreenNavigation (authViewModel: AuthViewModel = hiltViewModel()){

    Crossfade(targetState = Router.currentScreen, label = "") { currentState ->
        when (currentState.value) {
            Screen.SplashScreen -> SplashScreen()
            Screen.WelcomeScreen -> WelcomeScreen()
            Screen.ForgotPasswordResetLinkSentScreen -> ForgotPasswordScreenResetLinkSent()
            Screen.ForgotPasswordScreen -> ForgotPasswordScreen()
            Screen.LoginScreen -> LoginScreen()
            Screen.SignUpScreen -> SignUpScreen()
            Screen.HomeScreen -> Home()
            Screen.ContactUsScreen -> ContactUsScreen()
            Screen.GalleryScreen -> GalleryScreen()
            Screen.MyBatchScreen -> MyBatchesScreen()
            Screen.SavedLectureScreen -> SavedLectureScreen()
            Screen.QuizAndTestsScreen -> QuizAndTestsScreen()
            Screen.SavedNotesScreen -> SavedNotesScreen()
            is Screen.YoutubeLectureScreen -> {
                val videoString = (Router.currentScreen.value as Screen.YoutubeLectureScreen).videoString
                YoutuberLectureScreen(videoString)
            }
            Screen.ResultScreen -> ResultScreen()
            Screen.TermsAndConditionsScreen -> TermsAndConditionsScreen()
            is Screen.ExploreBatchScreen ->{
                val batch = (Router.currentScreen.value as Screen.ExploreBatchScreen).batch
                ExploreBatchScreen(batch = batch)
            }
            is Screen.LectureScreen -> {
                val lecture = (Router.currentScreen.value as Screen.LectureScreen).lecture
                LectureScreen(lecture)
            }
            is Screen.DetailBatchScreen ->{
                val batch = (Router.currentScreen.value as Screen.DetailBatchScreen).batch
                BatchDetailScreen(batch = batch)
            }
        }
    }
}