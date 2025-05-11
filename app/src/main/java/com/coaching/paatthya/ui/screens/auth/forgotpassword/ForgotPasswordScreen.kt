package com.coaching.paatthya.ui.screens.auth.forgotpassword

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.coaching.paatthya.R
import com.coaching.paatthya.domain.repository.UserErrorEvent
import com.coaching.paatthya.ui.error.ObserveEvents
import com.coaching.paatthya.ui.components.BackgroundImage
import com.coaching.paatthya.ui.components.RoundedButtonComponent
import com.coaching.paatthya.ui.components.HeadingTextComposable
import com.coaching.paatthya.ui.components.MyTextField
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.Spacing
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler
import com.coaching.paatthya.ui.theme.Primary
import com.coaching.paatthya.ui.theme.Secondary
import com.coaching.paatthya.ui.viewmodel.ForgotPasswordUiEvent
import com.coaching.paatthya.ui.viewmodel.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(forgotPasswordViewModel: ForgotPasswordViewModel = hiltViewModel()){
    val context = LocalContext.current
    ObserveEvents(flow = forgotPasswordViewModel.forgotPasswordErrorEvents) {
        when(it){
            is UserErrorEvent.Error -> {
                Toast.makeText(context, "Forgot Password Error: ${it.error.asString(context)}", Toast.LENGTH_SHORT).show()
            }
        }
    }
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
            RoundedButtonComponent(value = stringResource(R.string.send_reset_link)) {
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
            RoundedButtonComponent(value = stringResource(id = R.string.login)) {
                Router.navigateTo(Screen.LoginScreen)
            }
            SystemBackButtonHandler {
                Router.navigateTo(Screen.LoginScreen)
            }
        }
    }
}