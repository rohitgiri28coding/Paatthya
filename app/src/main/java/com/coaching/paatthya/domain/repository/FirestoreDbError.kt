package com.coaching.paatthya.domain.repository

sealed interface FirestoreDbError: Error {
    enum class DBError: FirestoreDbError{
        NO_INTERNET,
        SERVER_ERROR,
        UNKNOWN_ERROR
    }
    enum class NoticeError: FirestoreDbError{
        NO_INTERNET,
        SERVER_ERROR,
        UNKNOWN_ERROR
    }
}