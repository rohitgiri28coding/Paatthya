package com.coaching.srit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.screens.HomeScreen
import com.coaching.srit.ui.screens.WelcomeScreen
import com.coaching.srit.ui.screens.forgotpassword.ForgotPasswordScreen
import com.coaching.srit.ui.screens.forgotpassword.ForgotPasswordScreenResetLinkSent
import com.coaching.srit.ui.screens.login.LoginScreen
import com.coaching.srit.ui.screens.signup.SignUpScreen
import com.coaching.srit.ui.theme.SRITTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SRITTheme {
                App()
            }
        }
    }
    @Composable
    private fun App(){
        Surface(modifier = Modifier.fillMaxSize()) {
            Crossfade(targetState = Router.currentScreen, label = "") { currentState->
                when(currentState.value){
                    Screen.SignUpScreen -> {
                        SignUpScreen()
                    }
                    Screen.LoginScreen -> {
                        LoginScreen()
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
                        HomeScreen()
                    }
                }
            }
        }
    }
}