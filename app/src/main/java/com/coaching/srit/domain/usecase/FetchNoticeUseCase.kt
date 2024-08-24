package com.coaching.srit.domain.usecase

import com.coaching.srit.domain.FirestoreDbError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.repository.NoticeRepository
import com.coaching.srit.ui.viewmodel.home.Notice
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNoticeUseCase @Inject constructor(private val noticeRepository: NoticeRepository) {
    operator fun invoke(): Result<Flow<List<Notice>>, FirestoreDbError.NoticeError> {
        return noticeRepository.fetchNotice()
    }
}