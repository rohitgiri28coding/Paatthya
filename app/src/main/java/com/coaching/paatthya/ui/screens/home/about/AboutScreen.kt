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
                painter = painterResource(id = R.drawable.profile_blue_pic),
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.Center)
                    .border(1.dp, Color.DarkGray)
                    .size(width = 250.dp, height = 300.dp)
            )
        }
        Spacing(size = 25.dp)
        HeadingTextComposable(textValue = "About S.R.I.T.")
        DetailedTextComposable(text = "Welcome to S.R.I.T, your gateway to mastering the world of coding and technology. At Shubham Raj Info Tech, we specialize in offering comprehensive and modular computer courses designed to take you from a beginner to a professional in the IT sector. Whether you are looking to dive into ADCA, TALLY, Web Development, or Data Science, we have the perfect course tailored to meet your needs.\n" +
                    "\n" +
                    "Our curriculum is thoughtfully crafted to ensure that even students with no prior computer background can excel. We provide personalized learning experiences with an array of options including Computer Science (CS), Information Technology (IT), Online Services (such as Online Tests, Video Tutorials, and Online Materials), Industrial Training, Certification Courses, Workshops, Web Development, Project Training, and Job Placement Assistance")
        Spacing(size = 20.dp)
        NormalTextComposable(textValue = "Why Choose S.R.I.T?")
        DetailedTextComposable(text = "Experienced Leadership: Directed by Shubham Raj, with over 12 years of teaching experience and currently a lecturer at Patna University. Shubham Raj holds an MCA from L.N. Mishra Institute, Patna, and has placed students in top companies like Google, ISRO, Amazon, Infosys, Wipro, and TCS.\n" +
                "\n" +
                "Modern Facilities: Our campus features WI-FI, air conditioning, the latest computers, projectors, and internet facilities, ensuring a top-notch learning environment.\n" +
                "\n" +
                "Proven Success: Many students have progressed from zero knowledge to high-ranking IT positions, demonstrating our effective teaching methods.\n" +
                "\n" +
                "Comprehensive Support: We provide online services, industrial training, certification courses, workshops, project training, and job placement assistance.",
        )
        Spacing(size = 20.dp)
        NormalTextComposable(textValue = "About Our Director")
        DetailedTextComposable(text = "Shubham Raj, the visionary behind S.R.I.T, is renowned for his unique and effective teaching style. With a background rich in practical and academic achievements, Shubham Raj has worked on numerous projects under \"Beltron Bihar Government\" during his MCA tenure. His extensive experience and passion for teaching have enabled him to mentor and place hundreds of students in prestigious organizations.\n" +
                "\n" +
                "Join us at S.R.I.T and take the first step towards a successful IT career. Our doors are open to everyone, regardless of your prior experience with computers. Come, learn, and grow with us as we help you unlock your potential and achieve your professional goals.\n" +
                "\n" +
                "Welcome to S.R.I.T, where your future in technology begins.")
        Spacing()
    }
}