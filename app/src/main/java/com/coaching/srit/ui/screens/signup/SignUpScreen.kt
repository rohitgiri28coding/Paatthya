package com.coaching.srit.ui.screens.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.R
import com.coaching.srit.data.uievent.signup.SignUpUiEvent
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.ButtonComponent
import com.coaching.srit.ui.components.ClickableImageComposable
import com.coaching.srit.ui.components.ClickableLoginTextComponent
import com.coaching.srit.ui.components.HeadingTextComposable
import com.coaching.srit.ui.components.MyPasswordTextField
import com.coaching.srit.ui.components.MyTextField
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.TextDivider
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler
import com.coaching.srit.ui.theme.Primary

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()){
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
            Spacing(size = 50.dp)
            MyTextField(
                labelValue = stringResource(R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUiEvent.EmailChange(it))
                }
            )
            Spacing(size = 15.dp)
            MyPasswordTextField(
                labelValue = stringResource(R.string.new_password),
                painterResource = painterResource(id = R.drawable.password),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUiEvent.PasswordChange(it))
                }
            )
            Spacing()
            ButtonComponent(value = stringResource(R.string.sign_up), isEnabled = signUpViewModel.allValidationPassed.value) {
                signUpViewModel.onEvent(SignUpUiEvent.RegisterButtonClicked)
            }
            Spacing(size = 60.dp)
            TextDivider(text = "or connect using")
            Spacing(size = 60.dp)
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 60.dp)) {
                ClickableImageComposable(
                    img = R.drawable.google_logo,
                    contentDesc = "google",
                    padding = 8.dp
                )
                {

                }
                Column {
                    Spacing(size = 10.dp)
                    NormalTextComposable(
                        textValue = stringResource(R.string.sign_up_with_google),
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            ClickableLoginTextComponent(
                txt = stringResource(R.string.already_have_an_account),
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