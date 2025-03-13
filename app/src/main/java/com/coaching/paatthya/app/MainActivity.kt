package com.coaching.paatthya.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.coaching.paatthya.ui.navigation.AppScreenNavigation
import com.coaching.paatthya.ui.theme.PaatthyaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaatthyaTheme {
                AppScreenNavigation()
            }
        }
    }
}