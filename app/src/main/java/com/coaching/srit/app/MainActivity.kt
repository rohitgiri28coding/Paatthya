package com.coaching.srit.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.coaching.srit.ui.navigation.AppScreenNavigation
import com.coaching.srit.ui.theme.SRITTheme
import dagger.hilt.android.AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SRITTheme {
                AppScreenNavigation()
            }
        }
    }
}