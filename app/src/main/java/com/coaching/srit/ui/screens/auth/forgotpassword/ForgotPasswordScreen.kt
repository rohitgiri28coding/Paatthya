package com.coaching.srit.ui.screens.auth.forgotpassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.coaching.srit.R
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.ButtonComponent
import com.coaching.srit.ui.components.HeadingTextComposable
import com.coaching.srit.ui.components.MyTextField
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler
import com.coaching.srit.ui.theme.Primary
import com.coaching.srit.ui.theme.Secondary
import com.coaching.srit.ui.viewmodel.ForgotPasswordUiEvent
import com.coaching.srit.ui.viewmodel.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()){
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        Column(modifier = Modifier.padding(start = 20.dp, top = 40.dp, end = 20.dp)) {
            Spacing(size = 20.dp)
            HeadingTextComposable(
                textValue = stringResource(R.string.forgot_password)
            )
            Spacing(size = 60.dp)
            NormalTextComposable(
                textValue = stringResource(R.string.reset_password_msg),
                fontSize = 20.sp,
                color = Secondary,
                textAlign = TextAlign.Start,
            )
            Spacing()
            MyTextField(labelValue = stringResource(R.string.email), painterResource = painterResource(id = R.drawable.email)) {
                forgotPasswordViewModel.onEvent(ForgotPasswordUiEvent.EmailChanged(it))
            }
            Spacing(size = 40.dp)
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
            Spacing(size = 20.dp)
            HeadingTextComposable(
                textValue = stringResource(R.string.forgot_password),
                textAlign = TextAlign.Center
            )
            Spacing(size = 40.dp)
            NormalTextComposable(
                textValue = stringResource(R.string.reset_link_sent_msg),
                fontSize = 20.sp,
                color = Secondary,
                textAlign = TextAlign.Start,
            )
            Spacing(size = 40.dp)
            ButtonComponent(value = stringResource(id = R.string.login)) {
                Router.navigateTo(Screen.LoginScreen)
            }
            SystemBackButtonHandler {
                Router.navigateTo(Screen.LoginScreen)
            }
        }
    }
}