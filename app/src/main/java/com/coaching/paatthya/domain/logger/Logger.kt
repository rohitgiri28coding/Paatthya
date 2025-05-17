package com.coaching.paatthya.domain.logger

// domain/logger/Logger.kt

interface Logger {
    fun log(message: String)
    fun error(message: String, throwable: Throwable? = null)
}
