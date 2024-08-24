package com.coaching.srit.data.datasource

import android.util.Log
import com.coaching.srit.domain.FirestoreDbError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.NoticeRepository
import com.coaching.srit.ui.viewmodel.home.Notice
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore): NoticeRepository {

    private val _notices = MutableStateFlow<List<Notice>>(emptyList())

    override fun fetchNotice(): Result<Flow<List<Notice>>, FirestoreDbError.NoticeError> {
        try {
            val noticesQuery = firestore
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
            return Result.Success(_notices)
        } catch (e: Exception) {
            return Result.Error(FirestoreDbError.NoticeError.UNKNOWN_ERROR)
        }
    }

    override fun generateNewNotice(
        message: String,
        user: User
    ): Result<Unit, FirestoreDbError.NoticeError> {
        try {
            val noticeRef = firestore.collection("notices").document()       // Create a new document with auto-generated ID
            val dateTimeStamp = Timestamp.now()
            noticeRef.set(
                hashMapOf(
                    "name" to user.name,
                    "uid" to user.uid,
                    "message" to message.trim(),
                    "dateTimeStamp" to dateTimeStamp
                )
            )
            Log.d("message3",message)
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(FirestoreDbError.NoticeError.UNKNOWN_ERROR)
        }
    }
}