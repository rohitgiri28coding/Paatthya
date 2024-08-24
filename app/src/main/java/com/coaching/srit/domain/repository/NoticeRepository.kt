package com.coaching.srit.domain.repository

import com.coaching.srit.domain.FirestoreDbError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.ui.viewmodel.home.Notice
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    fun fetchNotice(): Result<Flow<List<Notice>>, FirestoreDbError.NoticeError>
    fun generateNewNotice(message: String, user: User): Result<Unit, FirestoreDbError.NoticeError>
}