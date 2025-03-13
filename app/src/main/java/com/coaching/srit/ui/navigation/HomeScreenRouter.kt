package com.coaching.srit.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class HomeScreen {
    data object StudyScreen: HomeScreen()
    data object AboutScreen: HomeScreen()
    data object NoticeScreen: HomeScreen()
    data object ViewAllBatchesScreen: HomeScreen()
}
object HomeScreenRouter{
    var currentScreen: MutableState<HomeScreen> = mutableStateOf(HomeScreen.StudyScreen)

    fun changeCurrentScreen(destination: HomeScreen){
        currentScreen.value = destination
    }
}