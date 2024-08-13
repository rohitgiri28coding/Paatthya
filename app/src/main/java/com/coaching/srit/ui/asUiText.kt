package com.coaching.srit.ui

import com.coaching.srit.R
import com.coaching.srit.domain.AuthError

fun AuthError.asUiText(): UiText {
    return when(this){
        AuthError.EmailValidationError.EMAIL_EMPTY-> {
            UiText.StringResource(
                R.string.email_cannot_be_empty
            )
        }

        AuthError.EmailValidationError.EMAIL_INVALID -> {
            UiText.StringResource(
                (R.string.enter_a_valid_email)
            )
        }
        AuthError.Local.DISK_FULL -> {
            TODO()
        }
        AuthError.Network.REQUEST_TIMEOUT -> {
            TODO()
        }
        AuthError.Network.TOO_MANY_REQUESTS -> {
            TODO()
        }
        AuthError.Network.NO_INTERNET -> {
            UiText.StringResource(R.string.no_internet_connection)
        }
        AuthError.Network.SERVER_ERROR -> {
            UiText.StringResource(R.string.server_error)
        }
        AuthError.Network.SERIALIZATION -> {
            TODO()
        }
        AuthError.Network.UNKNOWN_ERROR -> {
            UiText.StringResource(R.string.unknown_error)
        }

        AuthError.PasswordValidationError.TOO_SHORT -> {
            UiText.StringResource(R.string.too_short)
        }
        AuthError.PasswordValidationError.NO_SPECIAL_CHARACTER -> {
            UiText.StringResource(R.string.no_special_character)
        }
        AuthError.PasswordValidationError.NO_UPPERCASE -> {
            UiText.StringResource(R.string.no_uppercase)
        }
        AuthError.PasswordValidationError.NO_LOWERCASE -> {
            UiText.StringResource(R.string.no_lowercase)
        }
        AuthError.PasswordValidationError.NO_DIGIT -> {
            UiText.StringResource(R.string.no_digit)
        }
        AuthError.SignInError.NO_INTERNET -> {
            UiText.StringResource(R.string.no_internet_connection)
        }
        AuthError.SignInError.INVALID_CREDENTIALS -> {
            UiText.StringResource(R.string.invalid_credentials)
        }
        AuthError.SignInError.SERVER_ERROR -> {
            UiText.StringResource(R.string.server_error)
        }
        AuthError.SignInError.UNKNOWN_ERROR -> {
            UiText.StringResource(R.string.unknown_error)
        }
    }
}