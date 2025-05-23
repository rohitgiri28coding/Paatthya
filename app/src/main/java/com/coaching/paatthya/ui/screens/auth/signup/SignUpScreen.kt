package com.coaching.paatthya.ui.screens.auth.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.coaching.paatthya.ui.components.ClickableLoginTextComponent
import com.coaching.paatthya.ui.components.GoogleSignInButton
import com.coaching.paatthya.ui.components.HeadingTextComposable
import com.coaching.paatthya.ui.components.MyPasswordTextField
import com.coaching.paatthya.ui.components.MyTextField
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.Spacing
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler
import com.coaching.paatthya.ui.theme.Primary
import com.coaching.paatthya.ui.screens.auth.AuthUiEvent
import com.coaching.paatthya.ui.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = hiltViewModel()){
    val context = LocalContext.current
    ObserveEvents(flow = signUpViewModel.signUpErrorEvents) {
        when(it){
            is UserErrorEvent.Error -> {
                Toast.makeText(context, "Sign Up Error: ${it.error.asString(context)}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        Column(modifier = Modifier.padding(start = 20.dp, top = 80.dp, end = 20.dp)) {
            HeadingTextComposable(
                textValue = stringResource(R.string.hey_there)
            )
            Spacing(size = 15.dp)
            NormalTextComposable(
                textValue = stringResource(R.string.welcome_msg),
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
            Spacing(size = 40.dp)
            MyTextField(
                labelValue = stringResource(R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextSelected = {
                    signUpViewModel.onEvent(AuthUiEvent.EmailChange(it))
                }
            )
            Spacing(size = 15.dp)
            MyPasswordTextField(
                labelValue = stringResource(R.string.new_password),
                painterResource = painterResource(id = R.drawable.password),
                onTextSelected = {
                    signUpViewModel.onEvent(AuthUiEvent.PasswordChange(it))
                }
            )
            Spacing()
            RoundedButtonComponent(value = stringResource(R.string.sign_up)) {
                signUpViewModel.onEvent(AuthUiEvent.AuthButtonClicked)
            }
            GoogleSignInButton{
                signUpViewModel.onEvent(AuthUiEvent.GoogleAuthButtonClicked(context))
            }
            Spacer(modifier = Modifier.weight(1f))
            ClickableLoginTextComponent(
                text = stringResource(R.string.already_have_an_account),
                clickableText = stringResource(
                    id = R.string.login
                )
            ) {
                    Router.navigateTo(Screen.LoginScreen)
            }
            Spacing()
            SystemBackButtonHandler {
                Router.navigateTo(Screen.WelcomeScreen)
            }
        }
        if (signUpViewModel.signUpInProgress.value){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Primary)
        }
    }
}