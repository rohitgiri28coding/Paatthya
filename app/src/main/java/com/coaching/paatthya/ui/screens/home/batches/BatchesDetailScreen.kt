package com.coaching.paatthya.ui.screens.home.batches

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.coaching.paatthya.R
import com.coaching.paatthya.ui.components.BackgroundImage
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.TopAppBarWithBackButton
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler
import com.coaching.paatthya.ui.viewmodel.DetailBatchViewModel
import com.coaching.paatthya.ui.theme.Primary
import com.coaching.paatthya.domain.model.Batches
import com.coaching.paatthya.domain.model.ContentItem

@Composable
fun BatchDetailScreen(batch: Batches, detailBatchViewModel: DetailBatchViewModel = viewModel()) {
    var selectedTab by remember { mutableStateOf("Lectures") }
    val context = LocalContext.current
    Surface {
        BackgroundImage()
        Scaffold(
            topBar = {
                TopAppBarWithBackButton(text = batch.title)
            },
            containerColor = Color.Transparent
        ) {
            Box(modifier = Modifier.padding(it)) {
                Spacer(modifier = Modifier.height(50.dp).align(Alignment.TopCenter))

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Batch Image
                    AsyncImage(
                        model = batch.imgUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Batch Details
                    NormalTextComposable(textValue = batch.title)
                    NormalTextComposable(
                        textValue = "Start: ${batch.startDate} | End: ${batch.courseCompletionDate}",
                    )

                    Spacer(modifier = Modifier.height(4.dp))
                    NormalTextComposable(textValue = batch.description, fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    if (batch.limitedTimeDeal) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "🔥 Limited Time Batch!",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Tab Row
                    val tabs = listOf("Lectures", "Notes", "Assignments")
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(28.dp)
                    ) {
                        tabs.forEach { tab ->
                            Text(
                                text = tab,
                                modifier = Modifier
                                    .clickable { selectedTab = tab }
                                    .padding(bottom = 8.dp),
                                fontSize = 18.sp,
                                color = if (selectedTab == tab) Primary else Color.White,
                                fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                    Spacer(modifier = Modifier.height(12.dp))



                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        when (selectedTab) {
                            "Lectures" -> batch.lectures.mapIndexed { index, lecture ->
                                LectureCard(
                                    ContentItem(
                                        index = index + 1,
                                        title = lecture.lectureName,
                                    )
                                ) {
                                    Log.d("LectureCard", "Lecture Card Clicked $lecture")
//                                    if (lecture.isYTVideo){
                                    if (lecture.lectureLink == "8dEicZ0gMsw"){
                                        Router.navigateTo(Screen.YoutubeLectureScreen(lecture.lectureLink))
                                    }else {
                                        Router.navigateTo(Screen.LectureScreen(lecture))
                                    }
                                }
                            }

                            "Notes" -> batch.notes.mapIndexed { index, notes ->
                                ContentCard(
                                    detailBatchViewModel,
                                    ContentItem(
                                        index = index + 1,
                                        title = notes.notesName,
                                        fileUrl = notes.notesLink,
                                        fileType = "notes"
                                    ),
                                    context
                                )
                            }

                            "Assignments" -> batch.assignment.mapIndexed { index, assignment ->
                                ContentCard(
                                    detailBatchViewModel,
                                    ContentItem(
                                        index = index + 1,
                                        title = assignment.assignmentName,
                                        fileUrl = assignment.assignmentLink,
                                        fileType = "assignment"
                                    ),
                                    context
                                )
                            }
                        }

                    }
                }
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.MyBatchScreen)
    }
}

@Composable
fun LectureCard(item: ContentItem, onCardClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().background(
            brush = Brush.horizontalGradient(
                listOf(Color(0xFF1DE22D), Color(0xFF13751D))
            ),
            shape = RoundedCornerShape(50.dp)
        ).clickable{
            onCardClick.invoke()
        }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.email), // Replace with your own icon
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "L${item.index}: ${item.title}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
@Composable
fun ContentCard(viewModel: DetailBatchViewModel, item: ContentItem, context: Context) {
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

                if(viewModel.checkInDownloads(context, item.fileUrl, item.fileType)) {
                        if (downloadId == null) {
                            Log.d("DownloadContentScreen", "Download Started")
                            val fileName = item.fileUrl.substringAfterLast("/")
                            val newDownloadId =
                                viewModel.downloadFile(context, item.fileUrl, fileName, item.fileType)
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
                                    viewModel.openContentFile(
                                        context,
                                        item.fileUrl,
                                        item.fileType
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
                            Log.d("DownloadContentScreen", "Open File Started")
                            viewModel.openContentFile(context, item.fileUrl, item.fileType)
                        }
                }
            },
    ) {
        Column(
            modifier = Modifier.padding(16.dp).background(Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "L${item.index}: ${item.title}", style = MaterialTheme.typography.bodyMedium, color = Color.White)


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
            Log.d("DownloadContentScreen", "Resetting download state after error")
            downloadId = null
            downloadProgress = 0f
            downloadFinished = false
        }
    }
}

