package com.coaching.srit.ui.screens.home.study

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun StudyScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 10.dp, end = 5.dp), contentAlignment = Alignment.Center) {
//        Column (verticalArrangement = Arrangement.SpaceEvenly){
//            ClickableTextWithArrowSign(text = "Native App Development", imageVector = Icons.Default.KeyboardArrowDown) {
//
//            }
//            NormalTextComposable(textValue = "Today's Class", textAlign = TextAlign.Start)
//            Box (modifier = Modifier.border(2.dp, Color.DarkGray)){
//                NormalTextComposable(textValue = "No Class Scheduled")
//            }
//            ClickableTextWithArrowSign(text = "View All Classes") {
//
//            }
//            Divider(thickness = 3.dp, color = Color.DarkGray)
//            Spacer(modifier = Modifier.size(15.dp))
//            NormalTextComposable(textValue = "My Learning", textAlign = TextAlign.Start)
//
//            Column {
//
//            }
//        }
    }
}