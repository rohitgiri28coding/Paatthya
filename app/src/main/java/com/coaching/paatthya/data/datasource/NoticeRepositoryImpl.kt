package com.coaching.paatthya.data.datasource

import android.util.Log
import com.coaching.paatthya.domain.repository.FirestoreDbError
import com.coaching.paatthya.domain.repository.Result
import com.coaching.paatthya.domain.model.Notice
import com.coaching.paatthya.domain.repository.NoticeRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(private val firestore: FirebaseFirestore): NoticeRepository {

    private val _notices = MutableStateFlow<List<Notice>>(emptyList())

    override suspend fun fetchNotice(): Result<Flow<List<Notice>>, FirestoreDbError.NoticeError> {
        try {
            val noticesQuery = firestore
                .collection("notices")
                .orderBy(
                    "timestamp",
                    Query.Direction.DESCENDING
                )
            noticesQuery.addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    return@addSnapshotListener
                }
                val noticeList = snapshot?.toObjects(Notice::class.java) ?: emptyList()
                Log.d("FirestoreNotice", "Fetched notices: $noticeList")
                _notices.value = noticeList
            }
            return Result.Success(_notices)
        } catch (e: Exception) {
            Log.e("FirestoreNoticeError", "Error fetching notices: ${e.message}")
            return Result.Error(FirestoreDbError.NoticeError.UNKNOWN_ERROR)
        }
    }
}