package com.coaching.paatthya.domain.model

data class Notice(
    val title: String = "",
    val description: String = "",
    val fileUrl: String = "",
    val fileType: String = "",
    val isDownloaded: Boolean = false,
    val timestamp: Long = 0
)