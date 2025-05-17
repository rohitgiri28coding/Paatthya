package com.coaching.paatthya.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.coaching.paatthya.ui.screens.auth.splashscreen.SplashScreen

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AppEntryPoint() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen { showSplash = false }
    } else {
        AppScreenNavigation()
    }
}

