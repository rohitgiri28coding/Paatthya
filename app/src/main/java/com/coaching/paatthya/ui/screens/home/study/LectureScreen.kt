package com.coaching.paatthya.ui.screens.home.study

import android.os.Build
import android.view.WindowInsets
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.coaching.paatthya.ui.components.BackgroundImage
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.TopAppBarWithBackButton
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler
import com.coaching.paatthya.ui.viewmodel.home.Lecture


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun LectureScreen(lecture: Lecture) {
    Surface {
        BackgroundImage()
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = "Lecture Screen")
        },
            containerColor = Color.Transparent) {
            Column(modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())) {
                NormalTextComposable(
                    textValue = "Lecture Name: ${lecture.lectureName}",
                )
                VideoScreen(link = lecture.lectureLink)
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun VideoScreen(link: String) {
    var isFullScreen by remember { mutableStateOf(false) }

    if (isFullScreen) {
        FullScreenVideoPlayer(link) {
            isFullScreen = false
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Text(
                text = "Lecture Video",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
            Box {
                VideoPlayer(
                    videoUrl = link,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
                IconButton(
                    onClick = { isFullScreen = true },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Fullscreen,
                        contentDescription = "Full Screen",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun FullScreenVideoPlayer(videoUrl: String, onExitFullScreen: () -> Unit) {
    val activity = LocalActivity.current

    // Hide system UI
    LaunchedEffect(Unit) {
        activity?.window?.insetsController?.hide(WindowInsets.Type.systemBars())
    }

    // Show system UI again on exit
    DisposableEffect(Unit) {
        onDispose {
            activity?.window?.insetsController?.show(WindowInsets.Type.systemBars())
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        VideoPlayer(
            videoUrl = videoUrl,
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = onExitFullScreen,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FullscreenExit,
                contentDescription = "Exit Full Screen",
                tint = Color.White
            )
        }
    }
}


@Composable
fun VideoPlayer(videoUrl: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = androidx.media3.common.MediaItem.fromUri(videoUrl.toUri())
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
                useController = true // shows play/pause UI
            }
        },
        modifier = modifier
    )

    // Release player when not in use
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}