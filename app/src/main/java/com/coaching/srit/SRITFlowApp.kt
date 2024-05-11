package com.coaching.srit

import android.app.Application
import com.google.firebase.FirebaseApp

class SRITFlowApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}