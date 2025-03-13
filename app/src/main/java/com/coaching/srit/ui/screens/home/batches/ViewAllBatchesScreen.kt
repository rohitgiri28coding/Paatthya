package com.coaching.srit.ui.screens.home.batches

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.ui.components.BatchComponent
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.SwipeableBatchComponent
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.viewmodel.home.batch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ViewAllBatchesScreen(batchesViewModel: BatchesViewModel = viewModel()) {
    LazyColumn(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item { Spacing() }
        item {
            NormalTextComposable(
                textValue = "Limited Time Deal !!",
                fontSize = 24.sp,
            )
        }
        item { Spacing(size = 10.dp) }
        item { SwipeableBatchComponent(batchesViewModel.limitedTimeDealBatches.collectAsState().value) }
        item { Spacing() }
        item { HorizontalDivider() }
        item { Spacing(size = 20.dp) }
        item {
            NormalTextComposable(
                textValue = "Upcoming Events",
                fontSize = 24.sp,
            )
        }
        item { Spacing(size = 20.dp) }
        items(batchesViewModel.upcomingBatches.value) { batch ->
            BatchComponent(
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
        item {
            NormalTextComposable(
                textValue = "Ongoing Batches",
                fontSize = 24.sp,
            )
        }
        item { Spacing(size = 20.dp) }
        items(batchesViewModel.ongoingBatches.value) { batch ->
            BatchComponent(
                batch,
                onExploreButtonClick = {
                    Router.navigateTo(Screen.ExploreBatchScreen(batch))
                },
                onRedirectToPaymentPortal = {}
            )
        }
    }
}
