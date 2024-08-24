package com.coaching.srit.ui.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.coaching.srit.ui.screens.auth.forgotpassword.ForgotPasswordScreen
import com.coaching.srit.ui.screens.auth.forgotpassword.ForgotPasswordScreenResetLinkSent
import com.coaching.srit.ui.screens.auth.login.LoginScreen
import com.coaching.srit.ui.screens.auth.signup.SignUpScreen
import com.coaching.srit.ui.screens.auth.splashscreen.SplashScreen
import com.coaching.srit.ui.screens.auth.welcomescreen.WelcomeScreen
import com.coaching.srit.ui.screens.home.Home
import com.coaching.srit.ui.screens.home.contactus.ContactUsScreen
import com.coaching.srit.ui.screens.home.gallery.GalleryScreen
import com.coaching.srit.ui.screens.home.my_learning.MyBatchesScreen
import com.coaching.srit.ui.screens.home.my_learning.MyDoubtsScreen
import com.coaching.srit.ui.screens.home.my_learning.MyDownloadsScreen
import com.coaching.srit.ui.screens.home.my_learning.RecentlyWatchedScreen
import com.coaching.srit.ui.screens.home.result.ResultScreen
import com.coaching.srit.ui.screens.home.termsandconditions.TermsAndConditionsScreen
import com.coaching.srit.ui.viewmodel.AuthViewModel

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
            Screen.MyDoubtsScreen -> MyDoubtsScreen()
            Screen.MyDownloadsScreen -> MyDownloadsScreen()
            Screen.RecentlyWatchedScreen -> RecentlyWatchedScreen()
            Screen.ResultScreen -> ResultScreen()
            Screen.TermsAndConditionsScreen -> TermsAndConditionsScreen()
        }
    }
}