package com.coaching.paatthya.ui.screens.home.batches

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coaching.paatthya.ui.viewmodel.home.Batches
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ViewAllBatchesViewModel: ViewModel() {

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
        fetchBatchesRealtime()

    }

    private fun fetchBatchesRealtime() {
        viewModelScope.launch {
            val doc = db.collection("batches")
                .get().await()
            val batches = doc.toObjects(Batches::class.java)
            _batches.emit(batches)
            Log.d("BatchesSize", "Fetched ${batches.size} batches")
            Log.d("BatchesSize", "Fetched $batches batches")

            // Now that _batches is updated, update the filtered lists
            getLimitedTimedDealBatches()
            getOngoingBatches()
            getUpcomingBatches()
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


    private fun getCurrentDateFormatted(pattern: String = "dd/MM/yyyy"): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return currentDate.format(formatter)
    }

    private fun compareDates(date1: String, date2: String, pattern: String = "dd/MM/yyyy"): Int {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val localDate1 = LocalDate.parse(date1, formatter)
        val localDate2 = LocalDate.parse(date2, formatter)

        return localDate1.compareTo(localDate2)
    }

}
