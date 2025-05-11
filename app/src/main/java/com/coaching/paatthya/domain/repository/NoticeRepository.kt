package com.coaching.paatthya.domain.repository

import com.coaching.paatthya.domain.model.Notice
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    suspend fun fetchNotice(): Result<Flow<List<Notice>>, FirestoreDbError.NoticeError>
}