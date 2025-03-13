package com.coaching.srit.ui.screens.home.study

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
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coaching.srit.R
import com.coaching.srit.ui.components.ClickableImageComposable
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.theme.irishFont
import com.coaching.srit.ui.theme.kaushanScriptRegular
import com.coaching.srit.ui.theme.metalFont
import com.coaching.srit.ui.theme.metalMania
import com.coaching.srit.ui.theme.sedanRegular

@Composable
fun StudyScreen(){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Spacing()
        Column {
            Text(
                text = "HTML 1.0 2025",
                fontFamily = FontFamily(Font(R.font.irish_grover_regular)),
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 15.dp)
            )
            Spacing(size = 20.dp)
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
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
                        text = "No Class Scheduled",
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
            Row (modifier = Modifier.fillMaxWidth().padding(4.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                ClickableImageComposable(img = R.drawable.my_batches_tab_icon, contentDesc = "My Batches"){
                    Router.navigateTo(Screen.MyBatchScreen)
                }
                Spacing(4.dp)
                ClickableImageComposable(img = R.drawable.quiz_tab_icon, contentDesc = "Tests & Quiz"){
                    Router.navigateTo(Screen.MyDownloadsScreen)
                }

            }
            Spacing(size = 25.dp)
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                ClickableImageComposable(img = R.drawable.my_doubts_tab_icon, contentDesc = "My Doubts"){
                    Router.navigateTo(Screen.MyDoubtsScreen)
                }
                ClickableImageComposable(img = R.drawable.notes_tab_icon, contentDesc = "Notes"){
                    Router.navigateTo(Screen.RecentlyWatchedScreen)
                }
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