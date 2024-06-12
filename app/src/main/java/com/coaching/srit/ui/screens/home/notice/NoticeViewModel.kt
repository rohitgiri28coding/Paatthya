package com.coaching.srit.ui.screens.home.notice

import androidx.lifecycle.ViewModel
import com.coaching.srit.R
import com.coaching.srit.data.uievent.home.MessageSet
import com.coaching.srit.data.uievent.home.FormattedMessage
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NoticeViewModel: ViewModel() {
    private val messageSet = listOf(
        MessageSet(
            name = "Nishant",
            dateTimeStamp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).parse("11/06/2024 15:30:00")!!,
            message = listOf(
                "Celebrating Our Coaching Anniversary!",
            ),
            image = R.drawable.review_img
        ),
        MessageSet(
            name = "Shubham Raj",
            dateTimeStamp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).parse("10/05/2024 18:00:00")!!,
            message = listOf(
                "Tomorrow's Topic: Color Schemes in Jetpack Compose."
            ),
            image = R.drawable.profile_img
        ),
        MessageSet(
            name = "Nishant",
            dateTimeStamp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).parse("11/06/2024 16:30:00")!!,
            message = listOf(
                "Celebrating Our Coaching Anniversary!",
                "We are thrilled to announce that we will be celebrating our Coaching Anniversary! This special occasion marks another successful year of learning, growth, and achievements, and we want to celebrate it with all of you who have made it possible.",
            ),
            image = R.drawable.review_img
        ),
        MessageSet(
            name = "Shubham Raj",
            dateTimeStamp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).parse("9/05/2024 18:00:00")!!,
            message = listOf(
                "Tomorrow's Topic: Material3 in Jetpack Compose.",
                "Jetpack Compose offers an implementation of Material Design—a comprehensive design system for creating digital interfaces. The Material Design components (Buttons, Cards, Switches, etc) are built on top of Material Theming, which is a systematic way to customize Material Design to better reflect your product's brand"
            ),
            image = R.drawable.profile_img
        )
        )
    val formattedMessage = messageSet.sortedByDescending { it.dateTimeStamp }.groupBy {
        formatDate(it.dateTimeStamp)
    }.flatMap { (date, messages) ->
        messages.mapIndexed { index, message ->
            val showDate = index == messages.lastIndex
            FormattedMessage(
                name = message.name,
                time = formatTime(message.dateTimeStamp),
                date = if (showDate) date else "",
                message = message.message,
                image = message.image,
                id = message.dateTimeStamp
            )
        }
    }
    private fun formatTime(timeStamp: Date): String {
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return timeFormat.format(timeStamp)
    }
    private fun formatDate(timeStamp: Date): String {
        val today = Calendar.getInstance()
        val yesterday = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -1) }
        val messageDate = Calendar.getInstance().apply { time = timeStamp }

        return when {
            messageDate.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    messageDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) -> "Today"
            messageDate.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) &&
                    messageDate.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR) -> "Yesterday"
            else -> SimpleDateFormat("dd/MM/yy", Locale.getDefault()).format(timeStamp)
        }
    }
}