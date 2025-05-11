package com.coaching.paatthya.ui.screens.ytplayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.coaching.paatthya.ui.components.BackgroundImage
import com.coaching.paatthya.ui.components.TopAppBarWithBackButton
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler

@Composable
fun YoutuberLectureScreen(videoString: String) {
    val localLifecycle = LocalLifecycleOwner.current
    Surface {
        BackgroundImage()
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = "Lecture")
        },
            containerColor = Color.Transparent) {
            Column(modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())) {
                YoutubePlayer(videoString, localLifecycle)
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.MyBatchScreen)
    }
}