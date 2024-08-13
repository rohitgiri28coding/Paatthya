package com.coaching.srit.ui.viewmodel.home

import com.google.firebase.Timestamp

data class Notice(
    val name: String? = null,
    val uid: String? = null,
    val message: String? = null,
    val dateTimeStamp: Timestamp? = null
)