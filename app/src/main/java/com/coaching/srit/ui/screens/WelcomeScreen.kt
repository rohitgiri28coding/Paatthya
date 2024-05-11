package com.coaching.srit.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coaching.srit.R
import com.coaching.srit.ui.components.ButtonComponent
import com.coaching.srit.ui.components.HeadingTextComposable
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.components.TransparentButtonComponent
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen

@Composable
fun WelcomeScreen(){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_green),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(1f)
        )
        Column(modifier = Modifier
            .align(Alignment.Center)
            .padding(bottom = 100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.sritlogo),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier.size(120.dp)
            )
        }
        Column (modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 70.dp, start = 25.dp, end = 20.dp)){
            HeadingTextComposable(
                textValue = stringResource(R.string.welcome_to_srit),
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(3.dp))
            NormalTextComposable(
                textValue = buildString {
                    append(stringResource(R.string.programming_languages_sikhna_hua_asan))
                    append(stringResource(R.string.ab_crack_hoga_har_exam))
                },
                fontSize = 16.sp,
                textAlign = TextAlign.Left
            )
            Spacer(modifier = Modifier.height(40.dp))
            TransparentButtonComponent(value = stringResource(R.string.sign_in)) {
                Router.navigateTo(Screen.LoginScreen)
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(value = stringResource(R.string.sign_up)) {
                Router.navigateTo(Screen.SignUpScreen)
            }
        }
    }
}