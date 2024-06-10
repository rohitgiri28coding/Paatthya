package com.coaching.srit.ui.screens.home.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.HeadingTextComposable
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.TopAppBarWithBackButton
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler

@Composable
fun GalleryScreen() {
    Surface {
        BackgroundImage()
        Scaffold (
            topBar = {
                TopAppBarWithBackButton(text = "Gallery")
            }
        ){
            Column (modifier = Modifier.padding(it)){
                Spacing()
                Spacing()
                Spacing()
                HeadingTextComposable(textValue = "Gallery")
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}