package com.coaching.srit.ui.screens.home.batches

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.coaching.srit.R
import com.coaching.srit.ui.viewmodel.home.Batches
import com.coaching.srit.ui.viewmodel.home.batch
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class BatchesViewModel: ViewModel() {

    private val db = Firebase.firestore
    private val _batches = MutableStateFlow<List<Batches>>(emptyList())
    val batches: StateFlow<List<Batches>> = _batches
    private val _upcomingBatches: MutableStateFlow<List<Batches>> = MutableStateFlow(emptyList())
    val upcomingBatches: StateFlow<List<Batches>> = _upcomingBatches
    private val _ongoingBatches: MutableStateFlow<List<Batches>> = MutableStateFlow(emptyList())
    val ongoingBatches: StateFlow<List<Batches>> = _ongoingBatches
    private val _limitedTimeDealBatches: MutableStateFlow<List<Batches>> = MutableStateFlow(emptyList())
    val limitedTimeDealBatches: StateFlow<List<Batches>> = _limitedTimeDealBatches
    private val currentTime = getCurrentDateFormatted()

    init {
        _batches.value = batch
//        fetchBatchesRealtime()
        getLimitedTimedDealBatches()
        getOngoingBatches()
        getUpcomingBatches()
    }

    private fun fetchBatchesRealtime() {
        db.collection("batches")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { value, error ->
                if (error == null && value != null) {
                    val batchesList = value.documents.mapNotNull { it.toObject(Batches::class.java) }
                    _batches.value = batchesList
                }else{
                    Log.d("BatchViewModel", "Error fetching batches1: $error")
                }
            }
    }

    private fun isUpcomingBatch(startDate: String): Boolean{
        return compareDates(startDate, currentTime) >= 0
    }

    private fun getUpcomingBatches() {
        _upcomingBatches.value = _batches.value.filter { isUpcomingBatch(it.startDate) }
    }

    private fun getOngoingBatches(){
        _ongoingBatches.value = _batches.value.filter { !isUpcomingBatch(it.startDate) }
    }
    private fun getLimitedTimedDealBatches() {
        _limitedTimeDealBatches.value = _batches.value.filter { it.limitedTimeDeal }
    }


    private fun getCurrentDateFormatted(pattern: String = "dd-MM-yyyy"): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return currentDate.format(formatter)
    }

    private fun compareDates(date1: String, date2: String, pattern: String = "dd-MM-yyyy"): Int {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val localDate1 = LocalDate.parse(date1, formatter)
        val localDate2 = LocalDate.parse(date2, formatter)

        return localDate1.compareTo(localDate2)
    }

}

val images  = listOf(
    R.drawable.web_dev_banner,
    R.drawable.android_banner,
    R.drawable.banner
)
//val batches1 = listOf(
//    Batches(
//        title = "Android Development",
//        imgUrl = R.drawable.android_banner,
//        startDate = "19/04/2025",
//        courseCompletionDate = "25/06/2025",
//        price = 6000.0,
//        mrp = 10000.0,
//        description = "Learn to implement the server-side logic, creates RESTful APIs, and handles data retrieval, manipulation, and storage.\nain the skills required for an entry-level career as an Android developer.\n" +
//                "Learn how to create applications for Android including how to build and manage the lifecycle of a mobile app using Android Studio.\n" +
//                "Learn coding in Kotlin and the programming fundamentals for how to create the user interface (UI) and best practices for design.\n" +
//                "Create cross-platform mobile applications using React Native. Demonstrate your new skills by creating a job-ready portfolio you can show during interviews.\nTargeted students: Beginners and Intermediate.",
//    ),
//    Batches(
//        title = "Web Development",
//        imgUrl = R.drawable.web_dev_banner,
//        startDate = "19/03/2025",
//        courseCompletionDate = "25/05/2025",
//        price = 3000.0,
//        mrp = 5000.0,
//        description = "Learn to implement the server-side logic, creates RESTful APIs, and handles data retrieval, manipulation, and storage.\nDescribe the Web Application Development Ecosystem and terminology like front-end developer, back-end, server-side, and full stack.\nCreate and structure basic web pages using HTML and style them with CSS. \nIdentify the developer tools and integrated development environments (IDEs) used by web developers. \nTargeted students: Beginners and Intermediate."
//    )
//)

