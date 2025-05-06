package com.coaching.paatthya.domain.model

import com.google.firebase.Timestamp

data class Notice(
    val createdBy: String = "",
    val creatorUid: String = "",
    val title: String = "",
    val description: String = "",
    val fileUrl: String = "",
    val fileType: String = "",
    val timestamp: Timestamp
)