package com.coaching.paatthya.ui.screens.home.notice

sealed class NoticeUiEvent {
    data class MessageChange(val message: String): NoticeUiEvent()
    data object SendButtonClicked: NoticeUiEvent()
}