package com.coaching.srit.ui.screens.home.notice

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.ui.components.DateDivider
import com.coaching.srit.ui.components.GenerateFeedComponent
import com.coaching.srit.ui.components.Spacing

@Composable
fun NoticeScreen(noticeViewModel: NoticeViewModel = viewModel()) {
    val listState = rememberLazyListState()
    val formattedMessages = noticeViewModel.formattedMessage
    LazyColumn (state = listState, reverseLayout = true){
        items(formattedMessages, key = {it.id}) {
            Log.d("TAG", "NoticeScreen: $it")
            Spacing(size = 10.dp)
            GenerateFeedComponent(
                name = it.name,
                timeStamp = it.time,
                messages = it.message,
                image = it.image
            )
            if (it.date.isNotEmpty()) {
                Spacing(size = 20.dp)
                DateDivider(date = it.date)
            }
            Spacing(size = 10.dp)
        }
    }
}