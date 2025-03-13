package com.coaching.paatthya.ui.viewmodel

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel

class ContactUsViewModel: ViewModel() {
    fun makePhoneCall(context: Context, phoneNumber: String){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = "tel:$phoneNumber".toUri()

        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED

        ) {
            Log.d("Call", "need permission")
            ActivityCompat.requestPermissions(
                context as Activity,  // Cast context to Activity
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CODE_CALL_PHONE  // Define a unique request code
            )
        } else {
            context.startActivity(intent)
        }
    }

    fun sendMail(context: Context, emailAddress: String){
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = "mailto:$emailAddress".toUri()
        context.startActivity(intent)
    }
    fun openLocation(context: Context, location: String){
        val geoUri = "geo:0,0?q=$location".toUri()
        val intent = Intent(Intent.ACTION_VIEW, geoUri)
        context.startActivity(intent)
    }
    fun openWebsite(context: Context, url: String){
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        context.startActivity(intent)
    }
    companion object {
        private const val REQUEST_CODE_CALL_PHONE = 19
    }
}