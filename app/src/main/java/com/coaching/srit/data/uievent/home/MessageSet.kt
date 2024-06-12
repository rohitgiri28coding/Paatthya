package com.coaching.srit.data.uievent.home

import java.util.Date

data class MessageSet(
    val name: String,
    val dateTimeStamp: Date,
    val message: List<String>,
    val image: Int
)
data class FormattedMessage(
    val name: String,
    val date: String,
    val time: String,
    val message: List<String>,
    val image: Int,
    val id: Date
)