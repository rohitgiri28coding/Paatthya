package com.coaching.srit.domain.usecase

import com.coaching.srit.domain.FirestoreDbError
import com.coaching.srit.domain.Result
import com.coaching.srit.domain.model.User
import com.coaching.srit.domain.repository.NoticeRepository

class CreateNoticeUseCase(private val noticeRepository: NoticeRepository) {
    operator fun invoke(message: String, user: User): Result<Unit, FirestoreDbError.NoticeError> {
        return noticeRepository.generateNewNotice(message, user)
    }
}