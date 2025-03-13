package com.coaching.srit.ui.viewmodel.home

import java.util.UUID

data class Batches(
    val batchId: String = UUID.randomUUID().toString(),
    val title: String = "",
    val startDate: String,
    val courseCompletionDate: String="",
    val imgUrl: String = "",
    val price: Double=0.0,
    val mrp: Double=0.0,
    val description: String="",
    val limitedTimeDeal: Boolean = false
)


val batch = listOf( Batches(
    title = "Android App Development Masterclass",
    startDate = "04-10-2024",
    courseCompletionDate = "04-12-2024",
    imgUrl = "https://imagizer.imageshack.com/v2/310x550q70/r/661/Nt07gm.jpg",
    price = 99.99,
    mrp = 199.99,
    description = "Learn to build professional Android apps from scratch.",
    limitedTimeDeal = true
),
    Batches(
    title = "Full Stack Web Development Bootcamp",
    startDate = "04-12-2024",
    courseCompletionDate = "05-02-2025",
    imgUrl = "https://imagizer.imageshack.com/v2/366x550q70/r/673/zG2UbU.jpg",
    price = 149.99,
    mrp = 299.99,
    description = "Become a full-stack web developer with hands-on projects.",
    )
)