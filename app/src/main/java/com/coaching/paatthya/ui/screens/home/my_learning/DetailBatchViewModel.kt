package com.coaching.paatthya.ui.screens.home.my_learning

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class DetailBatchViewModel: ViewModel() {

    fun checkInDownloads(context: Context, url: String, type: String): Boolean {
        val fileName = url.substringAfterLast("/") // Extract filename from URL
        val file = File(Environment.getExternalStoragePublicDirectory("Download/Paatthya/batch/$type"), fileName)
        if (file.exists()) {
            openFile(context, file, type)
            return false
        }
        return true
    }
    fun downloadFile(context: Context, url: String, fileName: String, type: String): Long {
        val subfolder = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Paatthya/batch/$type")
        if (!subfolder.exists()) {
            subfolder.mkdirs()
        }

        val file = File(subfolder, fileName)
        val uri = Uri.fromFile(file)

        val request = DownloadManager.Request(url.toUri())
            .setTitle(fileName)
            .setDescription("Downloading file")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationUri(uri) // Use setDestinationUri()

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        return downloadManager.enqueue(request)
    }
    fun startProgressUpdates(context: Context, downloadId: Long, onProgress: (Float) -> Unit) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val handler = Handler(Looper.getMainLooper()) // Use main looper for UI updates

        val progressUpdater = object : Runnable {
            override fun run() {
                val query = DownloadManager.Query().setFilterById(downloadId)
                val cursor = downloadManager.query(query)

                if (cursor.moveToFirst()) {
                    val bytesDownloaded = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                    val bytesTotal = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))

                    if (bytesTotal > 0) {
                        val progress = bytesDownloaded.toFloat() / bytesTotal.toFloat()
                        onProgress(progress)

                        if (progress < 1f) {
                            handler.postDelayed(this, 1000) // Update every second
                        }
                    } else {
                        //bytes total is still zero, try again soon.
                        handler.postDelayed(this, 500)
                    }
                } else {
                    //cursor was empty, try again soon.
                    handler.postDelayed(this, 500)
                }
                cursor.close()
            }
        }

        // Delay the initial query slightly
        handler.postDelayed(progressUpdater, 500) // 500ms delay
    }

    fun openContentFile(context: Context, url: String, type: String) {
        val fileName = url.substringAfterLast("/")
        val file = File(Environment.getExternalStoragePublicDirectory("Download/Paatthya/batch/$type"), fileName)

        if (file.exists()) {
            openFile(context, file, type)
        } else {
            Log.d("DownloadContentScreen", "Download Started Again: $fileName")
            downloadFile(context, url, fileName, type)
        }
    }

    fun openFile(context: Context, file: File, type: String) {
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, getMimeType("pdf"))
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(Intent.createChooser(intent, "Open File"))
    }

    fun getMimeType(type: String): String {
        return when (type) {
            "pdf" -> "application/pdf"
            else -> "*/*"
        }
    }


    fun monitorDownload(
        context: Context,
        downloadId: Long,
        onComplete: () -> Unit,
        onError: (String) -> Unit,
        onCancel: () -> Unit // Add a cancel callback
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                val query = DownloadManager.Query().setFilterById(downloadId)
                val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val cursor: Cursor? = downloadManager.query(query)

                if (cursor != null && cursor.moveToFirst()) {
                    val status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))

                    when (status) {
                        DownloadManager.STATUS_SUCCESSFUL -> {
                            withContext(Dispatchers.Main) {
                                onComplete()
                            }
                            cursor.close()
                            break // Exit the loop
                        }
                        DownloadManager.STATUS_FAILED -> {
                            val reason = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_REASON))
                            withContext(Dispatchers.Main) {
                                onError("Download failed: $reason")
                            }
                            cursor.close()
                            break
                        }
                        else -> {
                            // Download is still in progress
                            delay(1000) // Check every second
                        }
                    }
                    cursor.close()
                } else {
                    withContext(Dispatchers.Main){
                        onError("Cursor was null")
                        onCancel.invoke()
                    }
                    break
                }
            }
        }
    }

}
