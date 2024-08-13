package com.coaching.srit.domain.model

data class User(
    val uid: String,
    val name: String = "Random One",
    val isAdmin: Boolean = false,
    val email: String
)