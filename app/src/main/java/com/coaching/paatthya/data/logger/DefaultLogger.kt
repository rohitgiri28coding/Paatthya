package com.coaching.paatthya.data.logger

import com.coaching.paatthya.domain.logger.Logger
import javax.inject.Inject

class DefaultLogger @Inject constructor() : Logger {
    override fun log(message: String) {
        println("LOG: $message")
    }

    override fun error(message: String, throwable: Throwable?) {
        println("ERROR: $message")
    }
}

