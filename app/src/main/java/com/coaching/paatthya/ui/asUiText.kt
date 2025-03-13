package com.coaching.paatthya.ui

import com.coaching.paatthya.R
import com.coaching.paatthya.domain.AuthError
import com.coaching.paatthya.domain.Error
import com.coaching.paatthya.domain.FirestoreDbError

fun Error.asUiText(): UiText {
    return when(this) {
        is AuthError -> this.asAuthUiText()
        is FirestoreDbError -> this.asFirestoreUiText()
        else -> UiText.StringResource(R.string.unknown_error)

    }
}

private fun AuthError.asAuthUiText(): UiText {
    return when(this){
        is AuthError.EmailValidationError -> this.asEmailValidationErrorUiText()
        is AuthError.Local -> this.asLocalUiText()
        is AuthError.Network -> this.asNetworkUiText()
        is AuthError.PasswordValidationError -> this.asPasswordValidationErrorUiText()
        is AuthError.SignInError -> this.asSignInErrorUiText()
        is AuthError.NameValidationError -> this.asNameValidationErrorUiText()
    }
}
private fun AuthError.EmailValidationError.asEmailValidationErrorUiText(): UiText {
    return when (this) {
        AuthError.EmailValidationError.EMAIL_EMPTY -> UiText.StringResource(R.string.email_cannot_be_empty)
        AuthError.EmailValidationError.EMAIL_INVALID -> UiText.StringResource(R.string.enter_a_valid_email)
    }
}
private fun AuthError.Local.asLocalUiText(): UiText {
    return when (this) {
        AuthError.Local.DISK_FULL -> UiText.StringResource(R.string.disk_full)
    }
}
private fun AuthError.Network.asNetworkUiText(): UiText {
    return when (this) {
        AuthError.Network.REQUEST_TIMEOUT -> UiText.StringResource(R.string.request_timeout)
        AuthError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(R.string.too_many_requests)
        AuthError.Network.NO_INTERNET -> UiText.StringResource(R.string.no_internet_connection)
        AuthError.Network.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
        AuthError.Network.SERIALIZATION -> UiText.StringResource(R.string.serialization_error)
        AuthError.Network.UNKNOWN_ERROR -> UiText.StringResource(R.string.unknown_error)
    }
}
private fun AuthError.PasswordValidationError.asPasswordValidationErrorUiText(): UiText {
    return when (this) {
        AuthError.PasswordValidationError.TOO_SHORT -> UiText.StringResource(R.string.too_short)
        AuthError.PasswordValidationError.NO_SPECIAL_CHARACTER -> UiText.StringResource(R.string.no_special_character)
        AuthError.PasswordValidationError.NO_UPPERCASE -> UiText.StringResource(R.string.no_uppercase)
        AuthError.PasswordValidationError.NO_LOWERCASE -> UiText.StringResource(R.string.no_lowercase)
        AuthError.PasswordValidationError.NO_DIGIT -> UiText.StringResource(R.string.no_digit)
    }
}
private fun AuthError.SignInError.asSignInErrorUiText(): UiText {
    return when (this) {
        AuthError.SignInError.NO_INTERNET -> UiText.StringResource(R.string.no_internet_connection)
        AuthError.SignInError.INVALID_CREDENTIALS -> UiText.StringResource(R.string.invalid_credentials)
        AuthError.SignInError.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
        AuthError.SignInError.UNKNOWN_ERROR -> UiText.StringResource(R.string.unknown_error)
    }
}
private fun AuthError.NameValidationError.asNameValidationErrorUiText(): UiText {
    return when (this) {
        AuthError.NameValidationError.NAME_EMPTY -> UiText.StringResource(R.string.name_cannot_be_empty)
        AuthError.NameValidationError.NAME_INVALID -> UiText.StringResource(R.string.invalid_name)
    }
}
private fun FirestoreDbError.asFirestoreUiText(): UiText {
    return when (this) {
        is FirestoreDbError.DBError -> this.asDbErrorUiText()
        is FirestoreDbError.NoticeError -> this.asNoticeErrorUiText()
    }
}

private fun FirestoreDbError.DBError.asDbErrorUiText(): UiText {
    return when (this) {
        FirestoreDbError.DBError.NO_INTERNET -> UiText.StringResource(R.string.no_internet_connection)
        FirestoreDbError.DBError.UNKNOWN_ERROR -> UiText.StringResource(R.string.unknown_error)
        FirestoreDbError.DBError.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
    }
}

private fun FirestoreDbError.NoticeError.asNoticeErrorUiText(): UiText {
    return when (this) {
        FirestoreDbError.NoticeError.NO_INTERNET -> UiText.StringResource(R.string.no_internet_connection)
        FirestoreDbError.NoticeError.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
        FirestoreDbError.NoticeError.UNKNOWN_ERROR -> UiText.StringResource(R.string.unknown_error)
    }
}