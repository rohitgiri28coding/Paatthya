package com.coaching.srit.ui.screens.forgotpassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.R
import com.coaching.srit.data.uievent.forgotpassword.ForgotPasswordUiEvent
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.ButtonComponent
import com.coaching.srit.ui.components.HeadingTextComposable
import com.coaching.srit.ui.components.MyTextField
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler
import com.coaching.srit.ui.theme.DarkGreen
import com.coaching.srit.ui.theme.Primary
import com.coaching.srit.ui.theme.Secondary

@Composable
fun ForgotPasswordScreen(forgotPasswordViewModel: ForgotPasswordViewModel= viewModel()){
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        Column(modifier = Modifier.padding(start = 20.dp, top = 40.dp, end = 20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComposable(
                textValue = stringResource(R.string.forgot_password)
            )
            Spacer(modifier = Modifier.height(60.dp))
            NormalTextComposable(
                textValue = stringResource(R.string.reset_password_msg),
                fontSize = 20.sp,
                color = Secondary,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(30.dp))
            MyTextField(labelValue = stringResource(R.string.email), painterResource = painterResource(id = R.drawable.email)) {
                forgotPasswordViewModel.onEvent(ForgotPasswordUiEvent.EmailChanged(it))
            }
            Spacer(modifier = Modifier.height(50.dp))
            ButtonComponent(value = stringResource(R.string.send_reset_link), isEnabled = forgotPasswordViewModel.allValidationPassed.value) {
                forgotPasswordViewModel.onEvent(ForgotPasswordUiEvent.ValidateResetPasswordButton)
            }
        }
        SystemBackButtonHandler {
            Router.navigateTo(Screen.LoginScreen)
        }
        if (forgotPasswordViewModel.isLoading.value){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Primary)
            }
        }
    }
}
@Composable
fun ForgotPasswordScreenResetLinkSent() {
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        Column(modifier = Modifier.padding(start = 20.dp, top = 40.dp, end = 20.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            HeadingTextComposable(
                textValue = stringResource(R.string.forgot_password),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            NormalTextComposable(
                textValue = stringResource(R.string.reset_link_sent_msg),
                fontSize = 20.sp,
                color = Secondary,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(50.dp))
            ButtonComponent(value = stringResource(id = R.string.login)) {
                Router.navigateTo(Screen.LoginScreen)
            }
            SystemBackButtonHandler {
                Router.navigateTo(Screen.LoginScreen)
            }
        }
    }
}