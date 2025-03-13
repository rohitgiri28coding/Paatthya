package com.coaching.paatthya.domain.usecase

import com.coaching.paatthya.domain.FirestoreDbError
import com.coaching.paatthya.domain.Result
import com.coaching.paatthya.domain.repository.NoticeRepository
import com.coaching.paatthya.domain.model.Notice
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNoticeUseCase @Inject constructor(private val noticeRepository: NoticeRepository) {
    operator fun invoke(): Result<Flow<List<Notice>>, FirestoreDbError.NoticeError> {
        return noticeRepository.fetchNotice()
    }
}