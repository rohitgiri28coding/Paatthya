package com.coaching.srit.ui.screens.login

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.R
import com.coaching.srit.data.uievent.login.LoginUiEvent
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.ButtonComponent
import com.coaching.srit.ui.components.HeadingTextComposable
import com.coaching.srit.ui.components.MyPasswordTextField
import com.coaching.srit.ui.components.MyTextField
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.components.UnderlinedTextComposable
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler
import com.coaching.srit.ui.theme.Primary

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        BackgroundImage()
        Column(modifier = Modifier.padding(start = 20.dp, top = 80.dp, end = 20.dp).align(Alignment.TopCenter) ) {
            HeadingTextComposable(
                textValue = stringResource(R.string.hi_there)
            )
            Spacer(modifier = Modifier.height(15.dp))
            NormalTextComposable(
                textValue = stringResource(R.string.welcome_msg),
                textAlign = TextAlign.Start,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(50.dp))
            MyTextField(
                labelValue = stringResource(R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUiEvent.EmailChange(it))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            MyPasswordTextField(
                labelValue = stringResource(R.string.password),
                painterResource = painterResource(id = R.drawable.password),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUiEvent.PasswordChange(it))
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            UnderlinedTextComposable(textValue = stringResource(R.string.forgot_your_password)) {
                Router.navigateTo(Screen.ForgotPasswordScreen)
            }
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(
                value = stringResource(id = R.string.login),
                isEnabled = loginViewModel.allValidationPassed.value
            )
            {
                loginViewModel.onEvent(LoginUiEvent.ValidateLoginButtonClicked)
            }
            SystemBackButtonHandler {
                Router.navigateTo(Screen.WelcomeScreen)
            }
            if (loginViewModel.loginInProgress.value){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally), color = Primary)
            }
            if(loginViewModel.invalidUser.value){
                Toast.makeText(LocalContext.current, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                loginViewModel.invalidUser.value = false
            }
        }
    }
}