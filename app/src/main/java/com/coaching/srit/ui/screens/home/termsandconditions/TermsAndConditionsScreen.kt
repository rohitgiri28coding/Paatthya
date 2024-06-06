package com.coaching.srit.ui.screens.home.termsandconditions

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.coaching.srit.ui.components.HeadingTextComposable

@Composable
fun TermsAndConditionsScreen() {
    Column {
        HeadingTextComposable(textValue = "Terms and Conditions", color = Color.Black)
    }
}