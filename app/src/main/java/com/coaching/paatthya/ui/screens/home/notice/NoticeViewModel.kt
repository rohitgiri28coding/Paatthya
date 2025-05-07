package com.coaching.paatthya.ui.screens.home.notice

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
import androidx.lifecycle.viewModelScope
import com.coaching.paatthya.domain.Result
import com.coaching.paatthya.domain.model.Notice
import com.coaching.paatthya.domain.usecase.FetchNoticeUseCase
import com.coaching.paatthya.ui.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    fetchNoticeUseCase: FetchNoticeUseCase
): ViewModel() {
    private val _notices = MutableStateFlow<List<Notice>>(emptyList())
    val notices: StateFlow<List<Notice>> = _notices

    init {
        viewModelScope.launch (Dispatchers.IO) {
            when (val notice = fetchNoticeUseCase.invoke()) {
                is Result.Error -> {
                    val error = notice.error.asUiText()
                    Log.d("NoticeViewModel", "Error fetching notices: $error")
                }

                is Result.Success -> {
                    val notices = notice.data
                    notices.collect {
                        _notices.value = it
                    }
                }
            }
        }
    }
    fun isDownloadable(fileType: String): Boolean {
        return fileType != "announcements" || fileType.isNotEmpty()
    }

    fun checkInDownloads(context: Context, url: String, type: String): Boolean {
        val fileName = url.substringAfterLast("/") // Extract filename from URL
        val file = File(Environment.getExternalStoragePublicDirectory("Download/Paatthya/notice"), fileName)
        if (file.exists()) {
            openFile(context, file, type)
            return false
        }
        return true
    }
    fun downloadFile(context: Context, url: String, fileName: String): Long {
        val subfolder = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Paatthya/notice")
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

    fun openNoticeFile(context: Context, url: String, type: String) {
        val fileName = url.substringAfterLast("/")
        val file = File(Environment.getExternalStoragePublicDirectory("Download/Paatthya/notice"), fileName)

        if (file.exists()) {
            openFile(context, file, type)
        } else {
            Log.d("DownloadNoticeScreen", "Download Started Again: $fileName")
            downloadFile(context, url, fileName)
        }
    }

    fun openFile(context: Context, file: File, type: String) {
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, getMimeType(type))
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(Intent.createChooser(intent, "Open File"))
    }

    fun getMimeType(type: String): String {
        return when (type) {
            "pdf" -> "application/pdf"
            "docx" -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            "image" -> "image/*"
            "announcements" -> ""
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

