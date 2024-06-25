package com.coaching.srit.ui.screens.splashscreen

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.coaching.srit.R
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen() {
    var navigateToWelcome by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = !navigateToWelcome) {
        delay(2000)
        navigateToWelcome = true
    }

    if (navigateToWelcome) {
        Router.navigateTo(Screen.WelcomeScreen)
        return
    }

    var rotationState by remember {
        mutableFloatStateOf(0f)
    }

    LaunchedEffect (rotationState){
        delay(2000)
        rotationState += 1f
    }

    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = TweenSpec(durationMillis = 500),
        label = "Logo Scale Animation"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        Image(
            painter = painterResource(id = R.drawable.sritlogo),
            contentDescription = "SRIT Logo",
            modifier = Modifier
                .align(Alignment.Center)
                .size(150.dp)
                .clip(CircleShape)
                .scale(scale)
                .rotate(rotationState)
        )
    }
}