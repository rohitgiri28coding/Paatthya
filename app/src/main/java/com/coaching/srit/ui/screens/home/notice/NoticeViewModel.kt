package com.coaching.srit.ui.screens.home.notice

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class NoticeViewModel: ViewModel() {

    private val _notices = MutableStateFlow<List<Notice>>(emptyList())
    val notices = _notices.asStateFlow()
    private val db = FirebaseFirestore.getInstance()
    private val noticesQuery =
        db.collection("notices").orderBy("dateTimeStamp", Query.Direction.ASCENDING)

    fun fetchNotices() {
        noticesQuery.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                Log.e("Firestore", "Error fetching notices: ${exception.message}")
                return@addSnapshotListener
            }
            val noticeList = snapshot?.toObjects(Notice::class.java) ?: emptyList()
            _notices.value = noticeList
            Log.d("fetching notice", "fetchNotices: $noticeList")
        }
    }

    fun generateNewNotice(message: String, name: String, uid: String) {

        val db = FirebaseFirestore.getInstance()
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

    fun formatTimestamp(timestamp: Timestamp): String {
        val date = timestamp.toDate()
        val formatter = SimpleDateFormat("HH:mm a", Locale.getDefault()) // 24-hour format
        return formatter.format(date)
    }


    fun formatDatestamp(timestamp: Timestamp): String {
        val date = timestamp.toDate()

        // Create a Calendar instance in the desired time zone
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.time = date

        val today = Calendar.getInstance(TimeZone.getDefault())
        today.set(Calendar.HOUR_OF_DAY, 0)
        today.set(Calendar.MINUTE, 0)
        today.set(Calendar.SECOND, 0)
        today.set(Calendar.MILLISECOND, 0)

        val yesterday = Calendar.getInstance(TimeZone.getDefault())
        yesterday.time = today.time
        yesterday.add(Calendar.DAY_OF_YEAR, -1)

        val formatter = SimpleDateFormat("dd/MM/yy", Locale.getDefault())

        return when {
            calendar.timeInMillis >= today.timeInMillis -> "Today"
            calendar.timeInMillis >= yesterday.timeInMillis -> "Yesterday"
            else -> formatter.format(date)
        }
    }
}
data class Notice(
    val name: String? = null,
    val message: String? = null,
    val dateTimeStamp: Timestamp? = null
)