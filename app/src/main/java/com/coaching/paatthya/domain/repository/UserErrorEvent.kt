package com.coaching.paatthya.domain.repository

import com.coaching.paatthya.ui.error.UiText

sealed interface UserErrorEvent {
    data class Error(val error: UiText): UserErrorEvent
}