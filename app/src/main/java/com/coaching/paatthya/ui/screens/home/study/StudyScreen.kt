package com.coaching.paatthya.ui.screens.home.study

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coaching.paatthya.R
import com.coaching.paatthya.ui.components.ButtonComponent
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.Spacing
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.theme.irishFont
import com.coaching.paatthya.ui.theme.kaushanScriptRegular
import com.coaching.paatthya.ui.theme.metalFont
import com.coaching.paatthya.ui.theme.metalMania
import com.coaching.paatthya.ui.theme.sedanRegular

@Composable
fun StudyScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Spacing()
        Column {

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                Text(
                    text = "Today's Class",
                    fontFamily = kaushanScriptRegular,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Spacing(size = 20.dp)
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Box(
                    modifier = Modifier
                        .border(2.dp, Color.White)
                        .size(300.dp, 80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Classes Scheduled",
                        fontFamily = irishFont,
                        fontSize = 23.sp,
                        color = Color(0xFFCACDD8)
                    )
                }
            }
            Spacing(size = 40.dp)
        }
        HorizontalDivider()
        Spacing()
        Column {
            NormalTextComposable(textValue = stringResource(R.string.my_learning),
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Left,
                startPadding = 10.dp
            )
            Spacing()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // Better than SpaceEvenly for controlled spacing
            ) {
                ButtonComponent(
                    text = "My Batches",
                    icon = R.drawable.batch_icon,
                    onClick = { Router.navigateTo(Screen.MyBatchScreen) },
                    modifier = Modifier.weight(1f)
                )
                ButtonComponent(
                    text = "Saved Lectures",
                    icon = R.drawable.lec_icon,
                    onClick = { Router.navigateTo(Screen.SavedLectureScreen) },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacing(10.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ButtonComponent(
                    text = "Quizes & Tests",
                    icon = R.drawable.test_icon,
                    onClick = { Router.navigateTo(Screen.QuizAndTestsScreen) },
                    modifier = Modifier.weight(1f)
                )
                ButtonComponent(
                    text = "Saved Notes",
                    icon = R.drawable.notes_icon,
                    onClick = { Router.navigateTo(Screen.SavedNotesScreen) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Spacing()
        HorizontalDivider()
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacing(size = 20.dp)
            Box {
                Text(text = buildAnnotatedString {
                    append("Coding krna hua ")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontFamily = metalMania
                        )
                    ){
                        append("AASAN")
                    }
                    append("\nAb crack hoga har ")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Green,
                            fontFamily = metalMania
                        )
                    ){
                        append("EXAM !!")
                    }
                },
                    color = Color.White,
                    fontSize = 22.sp,
                    fontFamily = metalFont,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 50.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.group_6),
                    contentDescription = "",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
            }
            Spacing(size = 10.dp)
            Text(text = "Made with ❤ by RASS", color = Color(0xFFCACDD8), fontFamily = sedanRegular)
        }
    }
}