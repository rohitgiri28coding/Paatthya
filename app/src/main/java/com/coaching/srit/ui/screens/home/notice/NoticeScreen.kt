package com.coaching.srit.ui.screens.home.notice

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.R
import com.coaching.srit.ui.viewmodel.home.Notice
import com.coaching.srit.ui.components.GenerateFeedComponent
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.TextDivider
import com.coaching.srit.ui.viewmodel.NoticeViewModel

data class FormattedMessage(val notice: Notice, val formattedDate: String, val formattedTime: String)

@Composable
fun NoticeScreen(isAdmin: Boolean, uid: String, name: String, noticeViewModel: NoticeViewModel = viewModel()) {
    val listState = rememberLazyListState()

    val notices = noticeViewModel.notices.collectAsState(initial = emptyList())

    val formattedNotice = notices.value
        .groupBy {
            noticeViewModel.formatDatestamp(it.dateTimeStamp!!)
        }
        .flatMap { (date, messages) ->
            messages.mapIndexed { index, message ->
                val showDate = index == messages.lastIndex
                FormattedMessage(
                    notice = Notice(message.name, message.uid, message.message, message.dateTimeStamp),
                    formattedTime = noticeViewModel.formatTimestamp(message.dateTimeStamp!!),
                    formattedDate = if (showDate) date else ""
                )
            }
        }
    noticeViewModel.fetchNotices()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (messages, chatBox) = createRefs()
        LazyColumn(state = listState,
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
            items(formattedNotice, key = { it.notice.dateTimeStamp.hashCode() }) {
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
            if (isAdmin) {
                OutlinedTextField(
                    value = noticeViewModel.textValue.value,
                    onValueChange = { noticeViewModel.textValue.value = it },
                    placeholder = {
                        Text(text = "Enter message", color = Color.White)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFF13751D),
                        focusedBorderColor = Color(0xFF1DE22D),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(6.dp)
                        .height(56.dp),
                )
                Box(
                    modifier = Modifier
                        .heightIn(32.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(Color(0xFF1DE22D), Color(0xFF13751D))
                            ),
                            shape = RoundedCornerShape(50.dp)
                        ), contentAlignment = Alignment.Center
                    ,
                ) {
                    Button(
                        onClick = {
                            noticeViewModel.generateNewNotice(
                                message = noticeViewModel.textValue.value,
                                name = name,
                                uid = uid
                            )
                            noticeViewModel.textValue.value = ""
                        }, enabled = noticeViewModel.enableSendButton(),
                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent
                        )
                    ) {
                        Text(text = "Send", color = Color.White)
                    }
                }
            }
        }
    }
}