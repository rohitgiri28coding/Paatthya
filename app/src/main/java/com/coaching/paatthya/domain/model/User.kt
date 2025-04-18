package com.coaching.paatthya.domain.model

data class User(
    val uid: String,
    val name: String = "Student",
    val batches: List<String> = emptyList(),
    val email: String
)