package com.coaching.srit.ui.screens.home.notice

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.coaching.srit.R
import com.coaching.srit.ui.viewmodel.home.Notice1

@Composable
fun NoticeScreen1(viewModel: NoticeViewModel1 = viewModel()) {
    val context = LocalContext.current
    val notices = viewModel.notices.collectAsState(emptyList())
    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.Transparent),
        state = rememberLazyListState(),
        reverseLayout = true
    ){
        items(notices.value) { notice ->
            Row {
                Image(
                    painter = painterResource(id = R.drawable.profile_img),
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

@Composable
fun NoticeCard(viewModel: NoticeViewModel1, notice: Notice1, context: Context) {
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

val sampleNotices = listOf(
    Notice1(
        title = "Important Announcement",
        description = "Meeting rescheduled to next Tuesday.",
        fileUrl = "https://example.com/meeting.pdf",
        fileType = "pdf",
        timestamp = System.currentTimeMillis()
    ),
    Notice1(
        title = "Holiday Notice",
        description = "Office will be closed on December 25th.",
        fileUrl = "https://imagizer.imageshack.com/v2/367x550q70/r/901/UMDxC8.jpg",
        fileType = "image",
        timestamp = System.currentTimeMillis() - 1000000
    ),
    Notice1(
        title = "Project Update",
        description = "Project progress report is now available.",
        fileUrl = "https://example.com/report.docx",
        fileType = "docx",
        timestamp = System.currentTimeMillis() - 2000000
    ),
    Notice1(
        title = "New Policy",
        description = "Please read the updated company policy.",
        fileUrl = "https://example.com/policy.txt",
        fileType = "announcements",
        timestamp = System.currentTimeMillis() - 3000000
    ),
    Notice1(
        title = "Training Session",
        description = "Details about the upcoming training session.",
        fileUrl = "https://example.com/training.pdf",
        fileType = "pdf",
        timestamp = System.currentTimeMillis() - 4000000
    ),
    Notice1(
        title = "Event Reminder",
        description = "Don't forget the company picnic this weekend!",
        fileUrl = "https://example.com/picnic.jpg",
        fileType = "image",
        timestamp = System.currentTimeMillis() - 5000000
    ),
    Notice1(
        title = "System Maintenance",
        description = "The system will be down for maintenance tonight.",
        fileUrl = "https://example.com/maintenance.txt",
        fileType = "announcements",
        timestamp = System.currentTimeMillis() - 6000000
    ),
    Notice1(
        title = "Software Update",
        description = "New software version is now available.",
        fileUrl = "https://example.com/update.docx",
        fileType = "docx",
        timestamp = System.currentTimeMillis() - 7000000
    ),
    Notice1(
        title = "Safety Guidelines",
        description = "Review the updated safety guidelines.",
        fileUrl = "https://example.com/safety.pdf",
        fileType = "pdf",
        timestamp = System.currentTimeMillis() - 8000000
    ),
    Notice1(
        title = "Team Meeting",
        description = "Weekly team meeting agenda.",
        fileUrl = "https://example.com/agenda.txt",
        fileType = "announcements",
        timestamp = System.currentTimeMillis() - 9000000
    )
)

