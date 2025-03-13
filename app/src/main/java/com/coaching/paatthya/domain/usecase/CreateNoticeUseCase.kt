package com.coaching.paatthya.domain.usecase

import com.coaching.paatthya.domain.FirestoreDbError
import com.coaching.paatthya.domain.Result
import com.coaching.paatthya.domain.model.User
import com.coaching.paatthya.domain.repository.NoticeRepository

class CreateNoticeUseCase(private val noticeRepository: NoticeRepository) {
    operator fun invoke(message: String, user: User): Result<Unit, FirestoreDbError.NoticeError> {
        return noticeRepository.generateNewNotice(message, user)
    }
}