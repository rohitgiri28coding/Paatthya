package com.coaching.srit.ui.screens.home.result

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.ui.components.ReviewComposable
import com.coaching.srit.ui.components.TopAppBarWithBackButton
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler
import com.coaching.srit.ui.viewmodel.ResultViewModel


@Composable
fun ResultScreen(resultViewModel: ResultViewModel = viewModel()){
    Box {
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = "Our Results", topAppBarColor = Color(0xFF121316))
        },
            containerColor = Color(0xFF121316)) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp, bottom = 25.dp)
                        .border(2.dp, Color.White)
                ) {
                    items(resultViewModel.reviewSamples, key = { item -> item.hashCode() }) { item ->
                        ReviewComposable(
                            review = item.review,
                            name = item.name,
                            designation = item.designation,
                            img = item.image
                        )
                    }
                }
            }
        }
    }

    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}