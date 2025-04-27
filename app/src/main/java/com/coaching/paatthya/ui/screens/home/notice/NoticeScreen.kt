package com.coaching.paatthya.ui.screens.home.notice

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.paatthya.R
import com.coaching.paatthya.domain.model.Notice

@Composable
fun NoticeScreen(viewModel: NoticeViewModel = viewModel()) {
    val context = LocalContext.current
    val notices = viewModel.notices.collectAsState(emptyList())
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (notices.value.isEmpty()) {
            CircularProgressIndicator()
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(Color.Transparent),
            state = rememberLazyListState(),
            reverseLayout = true
        ) {
            items(notices.value) { notice ->
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.paatthya_app_logo),
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(
                                CircleShape
                            ).align(Alignment.CenterVertically)
                            .border(2.dp, Color.DarkGray, CircleShape)
                            .size(50.dp)
                    )
                    NoticeCard(viewModel, notice, context)
                }
            }
        }
    }
}

@Composable
fun NoticeCard(viewModel: NoticeViewModel, notice: Notice, context: Context) {
    var downloadId by remember { mutableStateOf<Long?>(null) }
    var downloadProgress by remember { mutableFloatStateOf(0f) }
    var downloadFinished by remember { mutableStateOf(false) }
    var downloadError by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xAA222222)), // Semi-transparent dark
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color(0xFF2F63E7), RoundedCornerShape(18.dp)) // Soft blue border
            .clip(RoundedCornerShape(18.dp))
            .shadow(12.dp, RoundedCornerShape(18.dp))
            .clickable {

                if (viewModel.isDownloadable(notice.fileType)) {
                    if(viewModel.checkInDownloads(context, notice.fileUrl, notice.fileType)) {
                        if (downloadId == null) {
                            Log.d("DownloadNoticeScreen", "Download Started")
                            val fileName = notice.fileUrl.substringAfterLast("/")
                            val newDownloadId =
                                viewModel.downloadFile(context, notice.fileUrl, fileName)
                            downloadId = newDownloadId
                            downloadError = false
                            downloadFinished = false

                            viewModel.startProgressUpdates(context, newDownloadId) { progress ->
                                downloadProgress = progress
                            }

                            viewModel.monitorDownload(
                                context,
                                newDownloadId,
                                onComplete = {
                                    downloadFinished = true
                                    downloadId = null  // Reset after successful download
                                    viewModel.openNoticeFile(
                                        context,
                                        notice.fileUrl,
                                        notice.fileType
                                    )
                                    Toast.makeText(context, "Download complete", Toast.LENGTH_SHORT)
                                        .show()
                                },
                                onError = { errorMessage ->
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    downloadError = true
                                },
                                onCancel = {
                                    downloadError = true
                                }
                            )
                        } else if (downloadFinished) {
                            Log.d("DownloadNoticeScreen", "Open File Started")
                            viewModel.openNoticeFile(context, notice.fileUrl, notice.fileType)
                        }
                    }
                }
            },
    ) {
        Column(
            modifier = Modifier.padding(16.dp).background(Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                notice.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Pure white for high contrast
            )
            Text(
                notice.description,
                fontSize = 14.sp,
                color = Color(0xFFBBBBBB) // Softer white for readability
            )
            Text(
                notice.fileType.uppercase(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF66FF66) // Neon green to match the theme
            )

            if (downloadId != null && !downloadFinished && !downloadError) {
                LinearProgressIndicator(
                    progress = { downloadProgress },
                    color = Color(0xFF0AFD23), // Bright green to match background
                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))
                )
                Text(
                    text = "Downloading... ${ (downloadProgress * 100).toInt()}%",
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (downloadError) {
                Text(
                    text = "Download failed. Tap to retry.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }
        }
    }

    // Reset download state when an error occurs
    LaunchedEffect(downloadError) {
        if (downloadError) {
            Log.d("DownloadNoticeScreen", "Resetting download state after error")
            downloadId = null
            downloadProgress = 0f
            downloadFinished = false
        }
    }
}