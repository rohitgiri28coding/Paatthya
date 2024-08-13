package com.coaching.srit.ui.screens.home.batches

import androidx.lifecycle.ViewModel
import com.coaching.srit.R
import com.coaching.srit.ui.viewmodel.home.Batches

class BatchesViewModel: ViewModel() {
    val images  = listOf(
        R.drawable.web_dev_banner,
        R.drawable.android_banner,
        R.drawable.banner
    )
    val batches = listOf(
        Batches(
            title = "Android Development",
            img = R.drawable.android_banner,
            description = "Learn to implement the server-side logic, creates RESTful APIs, and handles data retrieval, manipulation, and storage.\nTargeted students: Beginners and Intermediate.\nStarts on 25 Jun 2024.\nCompletion on 15 Jun 2025 "
        ),
        Batches(
            title = "Full Stack Web Development",
            img = R.drawable.web_dev_banner,
            description = "Learn to implement the server-side logic, creates RESTful APIs, and handles data retrieval, manipulation, and storage.\nTargeted students: Beginners and Intermediate.\nStarts on 15 Jun 2024.\nCompletion on 15 Jun 2025 "
        )
    )
}