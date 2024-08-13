package com.coaching.srit.domain

import com.coaching.srit.ui.UiText

sealed interface UserErrorEvent {
    data class Error(val error: UiText): UserErrorEvent
}