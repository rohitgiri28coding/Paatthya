package com.coaching.srit.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coaching.srit.data.firebase.NoticeRepository
import com.coaching.srit.ui.viewmodel.home.Notice
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class NoticeViewModel: ViewModel() {

    var textValue = mutableStateOf("")

    private val noticeRepository = NoticeRepository()
    val notices: StateFlow<List<Notice>> = noticeRepository.notices

    fun fetchNotices() {
        noticeRepository.fetchNotices()
    }

    fun generateNewNotice(message: String, name: String, uid: String) {
        noticeRepository.generateNewNotice(message, name, uid)
    }

    fun enableSendButton(): Boolean {
        return textValue.value.isNotEmpty()
    }

    fun formatTimestamp(timestamp: Timestamp): String {
        val date = timestamp.toDate()
        val formatter = SimpleDateFormat("HH:mm a", Locale.getDefault()) // 24-hour format
        return formatter.format(date)
    }

    fun formatDatestamp(timestamp: Timestamp): String {
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