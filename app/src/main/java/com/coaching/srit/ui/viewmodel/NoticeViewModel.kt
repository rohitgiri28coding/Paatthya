package com.coaching.srit.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.UserErrorEvent
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.UserRepository
import com.coaching.srit.domain.usecase.CreateNoticeUseCase
import com.coaching.srit.domain.usecase.FetchNoticeUseCase
import com.coaching.srit.ui.asUiText
import com.coaching.srit.ui.screens.home.notice.FormattedNotice
import com.coaching.srit.ui.screens.home.notice.NoticeUiEvent
import com.coaching.srit.ui.screens.home.notice.NoticeUiState
import com.coaching.srit.ui.viewmodel.home.Notice
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(private val fetchNoticeUseCase: FetchNoticeUseCase, private val createNoticeUseCase: CreateNoticeUseCase, private val userRepository: UserRepository): ViewModel() {

    private val noticeUiState = mutableStateOf(NoticeUiState())

    private lateinit var user: User

    var isAdmin = mutableStateOf(false)
        private set

    private val _formattedNotices = MutableStateFlow<List<FormattedNotice>>(emptyList())
    var formattedNotice: StateFlow<List<FormattedNotice>> = _formattedNotices

    private val noticeErrorEventChannel = Channel<UserErrorEvent>()
    val noticeErrorEvents = noticeErrorEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUser().collectIndexed { _, value ->
                if (value != null) {
                    isAdmin.value = value.isAdmin
                    user = value
                    Log.d("user", user.toString())
                }
            }

        }
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = fetchNoticeUseCase.invoke()) {
                is Result.Error -> noticeErrorEventChannel.send(UserErrorEvent.Error(result.error.asUiText()))
                is Result.Success -> {
                    result.data.collect {
                        _formattedNotices.value = formatMessage(it)
                    }
                }
            }
        }
    }

    fun onEvent(event: NoticeUiEvent){
        when(event){
            is NoticeUiEvent.MessageChange -> {
                Log.d("TAG", "onEvent: ${event.message}")
                noticeUiState.value = noticeUiState.value.copy(
                    message = event.message
                )
            }
            NoticeUiEvent.SendButtonClicked -> {
                viewModelScope.launch {
                    createNoticeUseCase.invoke(noticeUiState.value.message, user)
                }
            }
        }
    }
    private fun formatMessage(notices: List<Notice>): List<FormattedNotice> {
        Log.d("TAG format", "formatMessage: $notices")
        return notices
            .groupBy {
                formatDatestamp(it.dateTimeStamp!!)
            }
            .flatMap { (date, messages) ->
                messages.mapIndexed { index, message ->
                    val showDate = index == messages.lastIndex
                    FormattedNotice(
                        notice = Notice(message.name, message.uid, message.message, message.dateTimeStamp),
                        formattedTime = formatTimestamp(message.dateTimeStamp!!),
                        formattedDate = if (showDate) date else ""
                    )
                }
            }
    }
    private fun formatTimestamp(timestamp: Timestamp): String {
        val date = timestamp.toDate()
        val formatter = SimpleDateFormat("HH:mm a", Locale.getDefault()) // 24-hour format
        return formatter.format(date)
    }

    private fun formatDatestamp(timestamp: Timestamp): String {
        val date = timestamp.toDate()

        // Create a Calendar instance in the desired time zone
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.time = date

        val today = Calendar.getInstance(TimeZone.getDefault())
        today.set(Calendar.HOUR_OF_DAY, 0)
        today.set(Calendar.MINUTE, 0)
        today.set(Calendar.SECOND, 0)
        today.set(Calendar.MILLISECOND, 0)

        val yesterday = Calendar.getInstance(TimeZone.getDefault())
        yesterday.time = today.time
        yesterday.add(Calendar.DAY_OF_YEAR, -1)

        val formatter = SimpleDateFormat("dd/MM/yy", Locale.getDefault())

        return when {
            calendar.timeInMillis >= today.timeInMillis -> "Today"
            calendar.timeInMillis >= yesterday.timeInMillis -> "Yesterday"
            else -> formatter.format(date)
        }
    }
}