package com.coaching.paatthya.domain.repository

import com.coaching.paatthya.domain.FirestoreDbError
import com.coaching.paatthya.domain.Result
import com.coaching.paatthya.domain.model.User
import com.coaching.paatthya.domain.model.Notice
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    suspend fun fetchNotice(): Result<Flow<List<Notice>>, FirestoreDbError.NoticeError>
}