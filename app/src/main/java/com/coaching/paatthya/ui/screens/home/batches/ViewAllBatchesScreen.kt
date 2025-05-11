package com.coaching.paatthya.ui.screens.home.batches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.PricedBatchComponent
import com.coaching.paatthya.ui.components.Spacing
import com.coaching.paatthya.ui.components.SwipeableBatchComponent
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.viewmodel.ViewAllBatchesViewModel

@Composable
fun ViewAllBatchesScreen(viewAllBatchesViewModel: ViewAllBatchesViewModel = viewModel()) {
    val limitedTimeBatch = viewAllBatchesViewModel.limitedTimeDealBatches.collectAsState()
    val upcomingBatches = viewAllBatchesViewModel.upcomingBatches.collectAsState()
    val ongoingBatches = viewAllBatchesViewModel.ongoingBatches.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (limitedTimeBatch.value.isEmpty() && upcomingBatches.value.isEmpty() && ongoingBatches.value.isEmpty()) {
            CircularProgressIndicator()
        }
        LazyColumn(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (limitedTimeBatch.value.isNotEmpty()) {
                item { Spacing() }
                item {
                    NormalTextComposable(
                        textValue = "Limited Time Deal !!",
                        fontSize = 24.sp,
                    )
                }
                item { Spacing(size = 10.dp) }
                item { SwipeableBatchComponent(limitedTimeBatch.value) }
                item { Spacing() }
                item { HorizontalDivider() }
                item { Spacing(size = 20.dp) }
            }
            if (upcomingBatches.value.isNotEmpty()) {
                item {
                    NormalTextComposable(
                        textValue = "Upcoming Events",
                        fontSize = 24.sp,
                    )
                }
                item { Spacing(size = 20.dp) }
                items(upcomingBatches.value) { batch ->
                    PricedBatchComponent(
                        batch,
                        onExploreButtonClick = {
                            Router.navigateTo(Screen.ExploreBatchScreen(batch))
                        },
                        onRedirectToPaymentPortal = {}
                    )
                }
                item { Spacing() }
                item { HorizontalDivider() }
                item { Spacing(size = 20.dp) }
            }
            if (ongoingBatches.value.isNotEmpty()) {
                item {
                    NormalTextComposable(
                        textValue = "Ongoing Batches",
                        fontSize = 24.sp,
                    )
                }
                item { Spacing(size = 20.dp) }
                items(ongoingBatches.value) { batch ->
                    PricedBatchComponent(
                        batch,
                        onExploreButtonClick = {
                            Router.navigateTo(Screen.ExploreBatchScreen(batch))
                        },
                        onRedirectToPaymentPortal = {}
                    )
                }
            }
        }
    }
}
