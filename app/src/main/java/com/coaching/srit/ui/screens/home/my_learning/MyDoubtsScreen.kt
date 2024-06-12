package com.coaching.srit.ui.screens.home.my_learning

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.TopAppBarWithBackButton
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler

@Composable
fun MyDoubtsScreen() {
    Surface {
        BackgroundImage()
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = "My Doubts")
        },
            containerColor = Color.Transparent) {
            Column(modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())) {

            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}