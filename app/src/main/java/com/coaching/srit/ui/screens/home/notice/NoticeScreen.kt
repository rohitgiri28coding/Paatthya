package com.coaching.srit.ui.screens.home.notice

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.coaching.srit.R
import com.coaching.srit.domain.UserErrorEvent
import com.coaching.srit.ui.ObserveEvents
import com.coaching.srit.ui.components.GenerateFeedComponent
import com.coaching.srit.ui.components.MessageBox
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.TextDivider
import com.coaching.srit.ui.viewmodel.NoticeViewModel
import com.coaching.srit.ui.viewmodel.home.Notice

data class FormattedNotice(val notice: Notice, val formattedDate: String, val formattedTime: String)

@Composable
fun NoticeScreen(noticeViewModel: NoticeViewModel = hiltViewModel()) {
    val formattedNotice = noticeViewModel.formattedNotice.collectAsState()

    val context = LocalContext.current
    ObserveEvents(flow = noticeViewModel.noticeErrorEvents) {
        when(it){
            is UserErrorEvent.Error -> {
                Toast.makeText(context, "Notice Error: ${it.error.asString(context)}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Box {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (messages, chatBox) = createRefs()
            LazyColumn(state = rememberLazyListState(),
                reverseLayout = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(messages) {
                        top.linkTo(parent.top)
                        bottom.linkTo(chatBox.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }
            ) {
                items(formattedNotice.value, key = { it.notice.dateTimeStamp.hashCode() }) {
                    Spacing(size = 10.dp)

                    if (it.notice.dateTimeStamp != null && it.notice.message != null) {

                        GenerateFeedComponent(
                            name = it.notice.name ?: "User",
                            timeStamp = it.formattedTime,
                            messages = it.notice.message,
                            image = R.drawable.profile_img
                        )
                        Spacing(size = 10.dp)
                        if (it.formattedDate != "") {
                            TextDivider(text = it.formattedDate)
                            Spacing(size = 20.dp)
                        }

                        Log.d("TAG", "NoticeScreen: $it")
                    }
                }

            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.constrainAs(chatBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }

            )
            {

                if (noticeViewModel.isAdmin.value) {
                    MessageBox(
                        onTextSelected = {
                            noticeViewModel.onEvent(NoticeUiEvent.MessageChange(it))
                        },
                        onSend = {
                            noticeViewModel.onEvent(NoticeUiEvent.SendButtonClicked)
                        }
                    )
                }
            }
        }
    }
}