package com.coaching.srit.data.firebase

import android.util.Log
import com.coaching.srit.ui.viewmodel.home.Notice
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoticeRepository {

    private val db = FirebaseFirestore.getInstance()

    private val _notices = MutableStateFlow<List<Notice>>(emptyList())
    val notices = _notices.asStateFlow()

    fun fetchNotices(){
        val noticesQuery = db
            .collection("notices")
            .orderBy(
                "dateTimeStamp",
                Query.Direction.DESCENDING
            )

        noticesQuery.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("Firestore", "Error fetching notices: ${exception.message}")
                return@addSnapshotListener
            }
            val noticeList = snapshot?.toObjects(Notice::class.java) ?: emptyList()
            _notices.value = noticeList
            Log.d("TAG", "fetchNotices: $noticeList")
        }


    }

    fun generateNewNotice(message: String, name: String, uid: String) {

        val noticeRef =
            db.collection("notices").document() // Create a new document with auto-generated ID
        val dateTimeStamp = Timestamp.now()

        noticeRef.set(
            hashMapOf(
                "name" to name,
                "uid" to uid,
                "message" to message.trim(),
                "dateTimeStamp" to dateTimeStamp
            )
        )
            .addOnSuccessListener {
                Log.d("Firestore", "Notice added successfully")
            }
            .addOnFailureListener { exception ->
                // Handle error adding notice
                Log.e("Firestore", "Error adding notice: ${exception.message}")
            }

    }
}