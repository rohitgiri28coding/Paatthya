package com.coaching.paatthya.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coaching.paatthya.domain.model.Batches
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MyBatchesViewModel : ViewModel() {

    private val db = Firebase.firestore
    private val _batches = MutableStateFlow<List<Batches>>(emptyList())
    val myBatch: StateFlow<List<Batches>> = _batches.asStateFlow()
    private val user = Firebase.auth.currentUser

    init {
        viewModelScope.launch {
            fetchAllBatches()
        }
    }

    private suspend fun fetchAllBatches() {
        val doc = db.collection("batches").get().await()
        doc.forEach {
            val batch = it.toObject(Batches::class.java)
            _batches.value += batch
            Log.d("MyBatchesViewModel", "Batch: ${batch.lectures}")
        }


    }

    private suspend fun fetchUserBatches() {
        user?.let {
            try {
                val doc = db.collection("users").document(it.uid).get().await()
                if (doc.exists()) {
                    val batches = doc.get("batches") as? List<String> ?: emptyList() // Safe cast and default
                    fetchBatchesRealtime(batches)
                } else {
                    println("User document not found.")
                }
            } catch (e: Exception) {
                println("Error fetching user myBatch: ${e.message}")
            }
        } ?: println("User not logged in.") // Log if user is null.
    }

    private suspend fun fetchBatchesRealtime(batchIdList: List<String>) {
        val fetchedBatches = mutableListOf<Batches>() // Avoid duplicates
        batchIdList.forEach { batchId ->
            try {
                val doc = db.collection("batches").document(batchId).get().await()
                val batch = doc.toObject(Batches::class.java)
                if (batch != null) {
                    fetchedBatches.add(batch)
                } else {
                    println("Batch document $batchId not found.")
                }
            } catch (e: Exception) {
                println("Error fetching batch $batchId: ${e.message}")
            }
        }
        _batches.value = fetchedBatches // Update StateFlow once
    }
}