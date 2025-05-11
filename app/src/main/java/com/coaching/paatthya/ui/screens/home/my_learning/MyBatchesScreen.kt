package com.coaching.paatthya.ui.screens.home.my_learning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.paatthya.ui.components.BackgroundImage
import com.coaching.paatthya.ui.components.MyBatchComponent
import com.coaching.paatthya.ui.components.Spacing
import com.coaching.paatthya.ui.components.TopAppBarWithBackButton
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler
import com.coaching.paatthya.ui.viewmodel.MyBatchesViewModel

@Composable
fun MyBatchesScreen(myBatchesViewModel: MyBatchesViewModel = viewModel()) {
    val myBatch = myBatchesViewModel.myBatch.collectAsState()
    Surface {
        BackgroundImage()
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = "My Batches")
        },
            containerColor = Color.Transparent) {
            if (myBatch.value.isNotEmpty()) {
                LazyColumn(modifier = Modifier.padding(it)) {
                    items(myBatch.value) { batch ->
                        Spacing(20.dp)
                        MyBatchComponent(
                            batch,
                            onExploreButtonClick = {
                                Router.navigateTo(Screen.DetailBatchScreen(batch))
                            }
                        )
                        Spacing()
                        HorizontalDivider()
                        Spacing(20.dp)
                    }
                }
            }
            else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}