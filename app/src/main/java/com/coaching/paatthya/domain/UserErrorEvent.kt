package com.coaching.paatthya.domain

import com.coaching.paatthya.ui.UiText

sealed interface UserErrorEvent {
    data class Error(val error: UiText): UserErrorEvent
}