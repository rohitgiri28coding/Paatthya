package com.coaching.paatthya.domain.model

data class ContentItem(
    val index: Int,
    val title: String,
    val fileUrl: String = "",
    val fileType: String = ""
)