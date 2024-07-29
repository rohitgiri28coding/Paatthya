package com.coaching.srit

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.coaching.srit.data.firebase.FirebaseBasicAuth
import com.coaching.srit.data.firebase.GoogleAuth
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.screens.WelcomeScreen
import com.coaching.srit.ui.screens.forgotpassword.ForgotPasswordScreen
import com.coaching.srit.ui.screens.forgotpassword.ForgotPasswordScreenResetLinkSent
import com.coaching.srit.ui.screens.home.Home
import com.coaching.srit.ui.screens.home.contactus.ContactUs
import com.coaching.srit.ui.screens.home.gallery.GalleryScreen
import com.coaching.srit.ui.screens.home.my_learning.MyBatchesScreen
import com.coaching.srit.ui.screens.home.my_learning.MyDoubtsScreen
import com.coaching.srit.ui.screens.home.my_learning.MyDownloadsScreen
import com.coaching.srit.ui.screens.home.my_learning.RecentlyWatchedScreen
import com.coaching.srit.ui.screens.home.result.ResultScreen
import com.coaching.srit.ui.screens.home.termsandconditions.TermsAndConditionsScreen
import com.coaching.srit.ui.screens.login.LoginScreen
import com.coaching.srit.ui.screens.signup.SignUpScreen
import com.coaching.srit.ui.screens.splashscreen.SplashScreen
import com.coaching.srit.ui.theme.SRITTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SRITTheme {
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                App(auth, context, scope)
            }
        }
    }

    @Composable
    fun App(
        auth: FirebaseAuth,
        context: Context,
        scope: CoroutineScope,
        firebaseBasicAuth: FirebaseBasicAuth = FirebaseBasicAuth(auth),
        googleAuth: GoogleAuth = GoogleAuth(context, auth),
    ) {
        var user by remember { mutableStateOf(auth.currentUser) }

        Router.chooseAuthOrHome(user)

        var showLoader by remember {
            mutableStateOf(false)
        }

        Surface(modifier = Modifier.fillMaxSize()) {

            Crossfade(targetState = Router.currentScreen, label = "") { currentState ->
                when (currentState.value) {
                    Screen.SplashScreen -> {
                        SplashScreen()
                    }

                    Screen.SignUpScreen -> {
                        SignUpScreen(
                            trySigningUp = { email, password ->
                                showLoader = true
                                scope.launch {
                                    user =
                                        firebaseBasicAuth.signUp(email = email, password = password)
                                }
                            },
                            trySigningUpUsingGoogle = {
                                showLoader = true
                                scope.launch {
                                    user = googleAuth.authenticateWithGoogle()
                                    Router.chooseAuthOrHome(user)
                                }
                            },
                            showLoader = showLoader
                        )
                    }

                    Screen.LoginScreen -> {
                        LoginScreen(
                            trySigningIn = { email, password ->
                                showLoader = true
                                scope.launch {
                                    user =
                                        firebaseBasicAuth.signIn(email = email, password = password)
                                }
                            },
                            trySigningInUsingGoogle = {
                                scope.launch {
                                    showLoader = true
                                    user = googleAuth.authenticateWithGoogle()
                                    Router.chooseAuthOrHome(user)
                                }
                            },
                            showLoader = showLoader
                        )
                    }

                    Screen.ForgotPasswordScreen -> {
                        ForgotPasswordScreen()
                    }

                    Screen.WelcomeScreen -> {
                        WelcomeScreen()
                    }

                    Screen.ForgotPasswordResetLinkSentScreen -> {
                        ForgotPasswordScreenResetLinkSent()
                    }

                    Screen.HomeScreen -> {
                        if (user != null) {
                            Home(
                                user = user!!,
                                onSignOut = {
                                    scope.launch {
                                        user = null
                                        googleAuth.signOutUser()
                                    }
                                }
                            )
                        }
                    }

                    Screen.ContactUsScreen -> {
                        ContactUs()
                    }

                    Screen.GalleryScreen -> {
                        GalleryScreen()
                    }

                    Screen.ResultScreen -> {
                        ResultScreen()
                    }

                    Screen.TermsAndConditionsScreen -> {
                        TermsAndConditionsScreen()
                    }

                    Screen.MyBatchScreen -> {
                        MyBatchesScreen()
                    }

                    Screen.MyDoubtsScreen -> {
                        MyDoubtsScreen()
                    }

                    Screen.MyDownloadsScreen -> {
                        MyDownloadsScreen()
                    }

                    Screen.RecentlyWatchedScreen -> {
                        RecentlyWatchedScreen()
                    }
                }
            }
        }
    }
}