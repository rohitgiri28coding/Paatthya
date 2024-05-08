package com.coaching.srit.ui.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.coaching.srit.ui.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(16.dp))
    {
//        HeadingTextComposable(textValue = stringResource(R.string.terms_of_use))
        SystemBackButtonHandler {
//            Router.navigateTo(Screen.SignUpScreen)
        }
    }
}