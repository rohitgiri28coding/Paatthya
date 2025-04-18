package com.coaching.paatthya.ui.viewmodel.home

import java.util.UUID

data class Batches(
    val batchId: String = UUID.randomUUID().toString(),
    val title: String = "",
    val startDate: String ="",
    val courseCompletionDate: String="",
    val imgUrl: String = "",
    val price: Double=0.0,
    val mrp: Double=0.0,
    val lectures: List<Lecture> = emptyList<Lecture>(),
    val notes: List<Notes> = emptyList<Notes>(),
    val assignment: List<Assignment> = emptyList<Assignment>(),
    val description: String="",
    val limitedTimeDeal: Boolean = false
){
    constructor() : this("","","","","", 0.0,0.0,emptyList(),emptyList(),emptyList(),"",false)
}


val batch = listOf(
    Batches(
        title = "Android App Development Masterclass",
        startDate = "04-10-2024",
        courseCompletionDate = "04-12-2024",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/android_banner.png",
        price = 1299.99,
        mrp = 1999.99,
        description = "Learn to build professional Android apps from scratch.",
        limitedTimeDeal = true
    ),
    Batches(
        title = "Full Stack Web Development Bootcamp",
        startDate = "04-12-2024",
        courseCompletionDate = "05-02-2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/web_dev_banner.png",
        price = 149.99,
        mrp = 299.99,
        description = "Become a full-stack web developer with hands-on projects.",
        limitedTimeDeal = true
    ),
    Batches(
        title = "Kotlin Multiplatform Crash Course",
        startDate = "20-06-2025",
        courseCompletionDate = "20-08-2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/android_banner.png",
        price = 799.99,
        mrp = 1499.99,
        description = "Build cross-platform apps using Kotlin Multiplatform.",
        limitedTimeDeal = true
    ),
    Batches(
        title = "React & Next.js Live Bootcamp",
        startDate = "20-06-2025",
        courseCompletionDate = "20-09-2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/web_dev_banner.png",
        price = 199.99,
        mrp = 499.99,
        description = "Master modern web development using React and Next.js.",
        limitedTimeDeal = true
    ),
    Batches(
        title = "Jetpack Compose for Beginners",
        startDate = "20-06-2025",
        courseCompletionDate = "20-07-2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/android_banner.png",
        price = 299.99,
        mrp = 699.99,
        description = "Learn modern Android UI with Jetpack Compose.",
        limitedTimeDeal = true
    )
)
