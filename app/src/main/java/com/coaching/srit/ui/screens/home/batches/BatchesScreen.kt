package com.coaching.srit.ui.screens.home.batches

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.ui.components.BatchComponent
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.SwipeableBatchComponent

@Composable
fun BatchesScreen(batchesViewModel: BatchesViewModel = viewModel()) {
    Column (modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(10.dp)){
        Spacing()
        NormalTextComposable(textValue = "Limited Time Deal !!", fontSize = 24.sp)
        Spacing(size = 10.dp)
        SwipeableBatchComponent(batchesViewModel.images)
        Spacing()
        HorizontalDivider()
        Spacing(size = 20.dp)
        NormalTextComposable(textValue = "Upcoming Events", fontSize = 24.sp)
        Spacing(size = 20.dp)
        batchesViewModel.batches.forEachIndexed { index, batch ->
            BatchComponent(title = batch.title, img = batch.img, detail = batch.description){}
            if (index == batchesViewModel.batches.lastIndex){
                Spacing(size = 20.dp)
            }else{
                Spacing()
            }
        }
    }
}