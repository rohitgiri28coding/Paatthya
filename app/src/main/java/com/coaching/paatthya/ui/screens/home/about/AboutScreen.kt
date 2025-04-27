package com.coaching.paatthya.ui.screens.home.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.coaching.paatthya.R
import com.coaching.paatthya.ui.components.DetailedTextComposable
import com.coaching.paatthya.ui.components.HeadingTextComposable
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.Spacing

@Composable
fun AboutScreen() {
    Column (modifier = Modifier.verticalScroll(rememberScrollState())){
        Spacing(size = 20.dp)
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.paatthya_app_logo),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .border(1.dp, Color.DarkGray)
                    .size(width = 250.dp, height = 300.dp)
            )
        }
        Spacing(size = 25.dp)
        HeadingTextComposable(textValue = "About Paatthya")
        DetailedTextComposable(text = "Welcome to Paatthya, your gateway to mastering the world with technology." +
                    "\n" +
                    "Our curriculum is thoughtfully crafted to ensure that even students with no prior knowledge can excel. We provide personalized learning experiences with an array of options Online Services (such as Online Tests, Video Tutorials, and Online Materials), Industrial Training, Certification Courses, Workshops.")
        Spacing(size = 20.dp)
        NormalTextComposable(textValue = "Why Choose Paatthya?")
        DetailedTextComposable(text = "Paatthya is a dedicated learning platform designed to make quality education accessible and organized for students. Our app connects students with experienced teachers, providing structured courses, easy access to study materials, and video lectures, all in one place. With a focus on simplicity, clarity, and academic excellence, Paatthya is your trusted companion on the journey to success.")

        Spacing()
    }
}