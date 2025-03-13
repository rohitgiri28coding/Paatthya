package com.coaching.paatthya.ui.screens.home.gallery

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.coaching.paatthya.R
import com.coaching.paatthya.ui.components.BackgroundImage
import com.coaching.paatthya.ui.components.GalleryRowComponent
import com.coaching.paatthya.ui.components.TopAppBarWithBackButton
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler

@Composable
fun GalleryScreen() {
    Surface {
        BackgroundImage()
        Scaffold (
            topBar = {
                TopAppBarWithBackButton(text = "Gallery")
            },
            containerColor = Color.Transparent
        ){
            Column (modifier = Modifier.padding(it).verticalScroll(rememberScrollState())){
                GalleryRowComponent(
                    img1 = R.drawable.class_img2,
                    img2 = R.drawable.facility2,
                    title1 = "Class",
                    title2 = "Facility",
                    onClick1 = {},
                    onClick2 = {}
                )
                GalleryRowComponent(
                    img1 = R.drawable.director,
                    img2 = R.drawable.review_img,
                    title1 = "Director",
                    title2 = "Alumni",
                    onClick1 = {},
                    onClick2 = {}
                )
                GalleryRowComponent(
                    img1 = R.drawable.gift2,
                    img2 = 0,
                    title1 = "Gift",
                    title2 = "",
                    onClick1 = {},
                    onClick2 = {}
                )
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}