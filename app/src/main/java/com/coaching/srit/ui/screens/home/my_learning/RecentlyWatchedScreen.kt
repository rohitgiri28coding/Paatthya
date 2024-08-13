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
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.TopAppBarWithBackButton
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler
import com.coaching.srit.ui.screens.home.YoutubePlayer

@Composable
fun RecentlyWatchedScreen() {
    Surface {
        BackgroundImage()
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = "Recently Watched")
        },
            containerColor = Color.Transparent) {
            Column(modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())) {
                Spacing()
                NormalTextComposable(textValue = "SRIT Introduction Video")
                Spacing()
                YoutubePlayer(
                    youtubeVideoId = "jHtJin50tJg",
                    lifecycleOwner = LocalLifecycleOwner.current
                )

            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}