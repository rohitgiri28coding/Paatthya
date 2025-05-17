package com.coaching.paatthya.domain.repository

sealed interface AuthError: Error {
    enum class Network: AuthError{
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN_ERROR
    }
    enum class DatabaseUpdateFailed: AuthError{
        FIRESTORE_ERROR,
        UNKNOWN_ERROR
    }
    enum class Local: AuthError{
        DISK_FULL
    }
    enum class SignInError: AuthError{
        NO_INTERNET,
        INVALID_CREDENTIALS,
        SERVER_ERROR,
        UNKNOWN_ERROR
    }
    enum class EmailValidationError: AuthError {
        EMAIL_EMPTY,
        EMAIL_INVALID,
    }
    enum class NameValidationError: AuthError {
        NAME_EMPTY,
        NAME_INVALID,
    }
    enum class PasswordValidationError: AuthError{
        TOO_SHORT,
        NO_SPECIAL_CHARACTER,
        NO_UPPERCASE,
        NO_LOWERCASE,
        NO_DIGIT
    }
}