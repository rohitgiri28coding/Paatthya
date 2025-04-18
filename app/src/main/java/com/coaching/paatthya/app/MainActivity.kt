package com.coaching.paatthya.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.coaching.paatthya.ui.navigation.AppScreenNavigation
import com.coaching.paatthya.ui.theme.PaatthyaTheme
import com.coaching.paatthya.ui.viewmodel.home.Assignment
import com.coaching.paatthya.ui.viewmodel.home.Batches
import com.coaching.paatthya.ui.viewmodel.home.Lecture
import com.coaching.paatthya.ui.viewmodel.home.Notes
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaatthyaTheme {
                AppScreenNavigation()
            }
        }
    }
}
fun upload(){
    val db = Firebase.firestore
    sampleBatches.forEach { batch->
        val b = hashMapOf(
            "batchId" to batch.batchId,
            "title" to batch.title,
            "startDate" to batch.startDate,
            "courseCompletionDate" to batch.courseCompletionDate,
            "imgUrl" to batch.imgUrl,
            "price" to batch.price,
            "mrp" to batch.mrp,
            "description" to batch.description,
            "limitedTimeDeal" to batch.limitedTimeDeal,
            "lectures" to batch.lectures,
            "notes" to batch.notes,
            "assignment" to batch.assignment
        )
        db.collection("batches").add(b).addOnSuccessListener {
            println("Success")
        }.addOnFailureListener {
            println("Failure")
        }
    }
}

val sampleBatches = listOf(
    Batches(
        title = "Android Development Bootcamp",
        startDate = "1 May 2025",
        courseCompletionDate = "30 July 2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/android_banner.png",
        price = 2499.0,
        mrp = 4999.0,
        description = "Master Android with Jetpack Compose and Firebase.",
        limitedTimeDeal = true,
        lectures = listOf(
            Lecture(
                dateTimeStamp = "2025-05-02",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Law+of+exponent+telling+untruth.mp4",
                lectureName = "Law of exponent",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            ),
            Lecture(
                dateTimeStamp = "2025-05-03",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Current+without+potential+difference.mp4",
                lectureName = "Current without potential difference",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            )
        ),
        notes = listOf(
            Notes(
                dateTimeStamp = "2025-05-01",
                notesName = "Android Overview Notes",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            ),
            Notes(
                dateTimeStamp = "2025-05-03",
                notesName = "Compose Cheatsheet",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            )
        ),
        assignment = listOf(
            Assignment(
                dateTimeStamp = "2025-05-04",
                assignmentName = "Install Android Studio & Setup Emulator",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            ),
            Assignment(
                dateTimeStamp = "2025-05-06",
                assignmentName = "Build a To-Do App UI",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            )
        )
    ),

    Batches(
        title = "Full Stack Web Development",
        startDate = "10 May 2025",
        courseCompletionDate = "15 August 2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/web_dev_banner.png",
        price = 2999.0,
        mrp = 5999.0,
        description = "HTML, CSS, JavaScript, Node.js, MongoDB in one course.",
        lectures = listOf(
            Lecture(
                dateTimeStamp = "2025-05-10",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Law+of+exponent+telling+untruth.mp4",
                lectureName = "Law of exponent",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            ),
            Lecture(
                dateTimeStamp = "2025-05-12",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Current+without+potential+difference.mp4",
                lectureName = "Current without potential difference",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            ),
        ),
        notes = listOf(
            Notes(
                dateTimeStamp = "2025-05-10",
                notesName = "HTML Summary",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            ),
            Notes(
                dateTimeStamp = "2025-05-12",
                notesName = "CSS Grid vs Flexbox",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            )
        ),
        assignment = listOf(
            Assignment(
                dateTimeStamp = "2025-05-13",
                assignmentName = "Design a Resume Webpage",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            ),
            Assignment(
                dateTimeStamp = "2025-05-15",
                assignmentName = "Style Blog Page",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            )
        )
    ),

    Batches(
        title = "Kotlin for Beginners",
        startDate = "5 May 2025",
        courseCompletionDate = "5 July 2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/android_banner.png",
        price = 1999.0,
        mrp = 3999.0,
        description = "From variables to coroutines — complete Kotlin journey.",
        lectures = listOf(
            Lecture(
                dateTimeStamp = "2025-05-05",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Law+of+exponent+telling+untruth.mp4",
                lectureName = "Law of exponent",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            ),
            Lecture(
                dateTimeStamp = "2025-05-07",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Current+without+potential+difference.mp4",
                lectureName = "Current without potential difference",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            )
        ),
        notes = listOf(
            Notes(
                dateTimeStamp = "2025-05-05",
                notesName = "Kotlin Syntax Notes",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            ),
            Notes(
                dateTimeStamp = "2025-05-07",
                notesName = "Kotlin OOP Notes",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            )
        ),
        assignment = listOf(
            Assignment(
                dateTimeStamp = "2025-05-08",
                assignmentName = "Simple Calculator in Kotlin",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            ),
            Assignment(
                dateTimeStamp = "2025-05-10",
                assignmentName = "Bank Account Class Design",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            )
        )
    ),

    Batches(
        title = "Java Programming Masterclass",
        startDate = "15 May 2025",
        courseCompletionDate = "15 August 2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/android_banner.png",
        price = 2199.0,
        mrp = 4499.0,
        description = "Learn Java from scratch with projects and certifications.",
        lectures = listOf(
            Lecture(
                dateTimeStamp = "2025-05-15",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Law+of+exponent+telling+untruth.mp4",
                lectureName = "Law of exponent",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            ),
            Lecture(
                dateTimeStamp = "2025-05-17",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Current+without+potential+difference.mp4",
                lectureName = "Current without potential difference",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            )
        ),
        notes = listOf(
            Notes(
                dateTimeStamp = "2025-05-15",
                notesName = "Java Basics",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            ),
            Notes(
                dateTimeStamp = "2025-05-17",
                notesName = "If-Else, Loops in Java",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            )
        ),
        assignment = listOf(
            Assignment(
                dateTimeStamp = "2025-05-18",
                assignmentName = "Write a Number Guessing Game",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            ),
            Assignment(
                dateTimeStamp = "2025-05-20",
                assignmentName = "Pattern Programs in Java",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            )
        )
    ),

    Batches(
        title = "ReactJS with Projects",
        startDate = "20 May 2025",
        courseCompletionDate = "20 August 2025",
        imgUrl = "https://sritbucket.s3.ap-south-1.amazonaws.com/web_dev_banner.png",
        price = 2599.0,
        mrp = 5199.0,
        description = "Build modern UIs using React, JSX, Hooks, and Context API.",
        lectures = listOf(
            Lecture(
                dateTimeStamp = "2025-05-20",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Law+of+exponent+telling+untruth.mp4",
                lectureName = "Law of exponent",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            ),
            Lecture(
                dateTimeStamp = "2025-05-22",
                lectureLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Current+without+potential+difference.mp4",
                lectureName = "Current without potential difference",
                lectureDetail = "This lecture is taught by HC Verma Sir.",
                isYTVideo = false
            )
        ),
        notes = listOf(
            Notes(
                dateTimeStamp = "2025-05-20",
                notesName = "JSX Basics",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            ),
            Notes(
                dateTimeStamp = "2025-05-22",
                notesName = "Props vs State",
                notesLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/Tutorial+1+Android+Studio+Hello+World.pdf"
            )
        ),
        assignment = listOf(
            Assignment(
                dateTimeStamp = "2025-05-23",
                assignmentName = "Create a Counter App",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            ),
            Assignment(
                dateTimeStamp = "2025-05-25",
                assignmentName = "Build a Todo List with State",
                assignmentLink = "https://sritbucket.s3.ap-south-1.amazonaws.com/13_Jetpack+Compose+State.pdf"
            )
        )
    )
)

