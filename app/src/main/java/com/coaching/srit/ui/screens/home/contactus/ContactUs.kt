package com.coaching.srit.ui.screens.home.contactus

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.coaching.srit.ui.components.HeadingTextComposable

@Composable
fun ContactUs() {
    Column {
        HeadingTextComposable(textValue = "Contact Us ", color = Color.Black)
    }
}