package com.coaching.srit.ui.screens.signup

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.R
import com.coaching.srit.data.uievent.signup.SignUpUiEvent
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.ButtonComponent
import com.coaching.srit.ui.components.MyPasswordTextField
import com.coaching.srit.ui.components.MyTextField
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler
import com.coaching.srit.ui.theme.Primary

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()){
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        Column(modifier = Modifier.padding(start = 20.dp, top = 40.dp, end = 20.dp)) {
            Spacer(modifier = Modifier.height(20.dp))
            NormalTextComposable(
                textValue = stringResource(R.string.hey_there)
            )
            NormalTextComposable(
                textValue = stringResource(R.string.create_an_account)
            )
            Spacer(modifier = Modifier.height(40.dp))
            MyTextField(
                labelValue = stringResource(R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUiEvent.EmailChange(it))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            MyPasswordTextField(
                labelValue = stringResource(R.string.new_password),
                painterResource = painterResource(id = R.drawable.password),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUiEvent.PasswordChange(it))
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(value = stringResource(R.string.sign_up), isEnabled = signUpViewModel.allValidationPassed.value) {
                signUpViewModel.onEvent(SignUpUiEvent.RegisterButtonClicked)
            }
            SystemBackButtonHandler {
                Router.navigateTo(Screen.WelcomeScreen)
            }
        }
        if (signUpViewModel.signUpInProgress.value){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Primary)
        }
    }
}