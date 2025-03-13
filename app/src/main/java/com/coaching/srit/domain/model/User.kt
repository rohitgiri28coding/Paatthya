package com.coaching.srit.domain.model

data class User(
    val uid: String,
    val name: String = "Admin",
    val isAdmin: Boolean = false,
    val batches: List<String> = emptyList(),
    val email: String
)