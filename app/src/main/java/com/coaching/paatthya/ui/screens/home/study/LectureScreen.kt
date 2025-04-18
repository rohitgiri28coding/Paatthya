package com.coaching.paatthya.ui.screens.home.study

import android.os.Build
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_OFF
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.coaching.paatthya.ui.components.BackgroundImage
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.Spacing
import com.coaching.paatthya.ui.components.TopAppBarWithBackButton
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler
import com.coaching.paatthya.ui.viewmodel.home.Lecture

@androidx.annotation.OptIn(UnstableApi::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun LectureScreen(lecture: Lecture) {
    val context = LocalContext.current

    // Create and remember ExoPlayer instance
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setSeekForwardIncrementMs(10000)
            .setSeekBackIncrementMs(10000)
            .build().apply {
                setMediaItem(MediaItem.fromUri(lecture.lectureLink))
                prepare()
                playWhenReady = true
                repeatMode = REPEAT_MODE_OFF
            }
    }

    // Handle back press
    SystemBackButtonHandler {
        Router.navigateTo(Screen.MyBatchScreen)
    }

    // Release player on dispose
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    VideoScreen(exoPlayer = exoPlayer, lecture.lectureName)
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun VideoScreen(exoPlayer: ExoPlayer, lectureName: String) {
    var isFullScreen by remember { mutableStateOf(false) }
    val activity = LocalActivity.current

    // Manage fullscreen system bars
    LaunchedEffect(isFullScreen) {
        activity?.window?.insetsController?.let { controller ->
            if (isFullScreen) {
                controller.hide(WindowInsets.Type.systemBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            } else {
                controller.show(WindowInsets.Type.systemBars())
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        Scaffold(
            topBar = {
                if (!isFullScreen) {
                    TopAppBarWithBackButton(text = "Lecture Screen")
                }
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()) {

                if (isFullScreen) {
                    FullScreenVideoPlayer(exoPlayer) { isFullScreen = false }
                } else {
                    Column {
                        NormalVideoPlayer(exoPlayer) { isFullScreen = true }
                        Spacing(5.dp)
                        HorizontalDivider()
                        Spacing(16.dp)
                        NormalTextComposable(lectureName)
                    }
                }
            }
        }
    }
}

@Composable
fun NormalVideoPlayer(
    exoPlayer: ExoPlayer,
    onFullscreenRequested: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .background(Color.Black)
    ) {
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    player = exoPlayer
                    useController = true
                }
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        IconButton(
            onClick = onFullscreenRequested,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Fullscreen,
                contentDescription = "Enter Fullscreen",
                tint = Color.White
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun FullScreenVideoPlayer(
    exoPlayer: ExoPlayer,
    onExitRequested: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    player = exoPlayer
                    useController = true
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        IconButton(
            onClick = onExitRequested,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FullscreenExit,
                contentDescription = "Exit Fullscreen",
                tint = Color.White
            )
        }
    }
}
