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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.R
import com.coaching.srit.ui.components.GenerateFeedComponent
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.TextDivider

@Composable
fun NoticeScreen(isAdmin: Boolean, uid: String, name: String,noticeViewModel: NoticeViewModel = viewModel()) {
    val listState = rememberLazyListState()
    var textValue by remember {
        mutableStateOf("")
    }
    var dateValue = ""
    val notices = noticeViewModel.notices.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        noticeViewModel.fetchNotices()
    }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (messages, chatBox) = createRefs()
        LazyColumn(state = listState,
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
            items(notices.value, key = { it.dateTimeStamp.hashCode() }) {
                Spacing(size = 10.dp)

                if (it.dateTimeStamp != null && it.message != null) {
                    val date = noticeViewModel.formatDatestamp(it.dateTimeStamp)
                    if (dateValue != date) {
                        TextDivider(text = date)
                        Spacing(size = 20.dp)
                        dateValue = date
                    }
                    Log.d("if TAG", "NoticeScreen: $it")
                    GenerateFeedComponent(
                        name = it.name ?: "User",
                        timeStamp = noticeViewModel.formatTimestamp(it.dateTimeStamp),
                        messages = it.message,
                        image = R.drawable.profile_img
                    )
                    Spacing(size = 10.dp)

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
                    value = textValue,
                    onValueChange = { textValue = it },
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
                                message = textValue,
                                name = name,
                                uid = uid
                            )
                            textValue = ""
                        }, enabled = textValue.isNotEmpty(),
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