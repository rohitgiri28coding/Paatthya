package com.coaching.paatthya.ui.screens.home.batches

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coaching.paatthya.ui.components.BackgroundImage
import com.coaching.paatthya.ui.components.RoundedButtonComponent
import com.coaching.paatthya.ui.components.NormalTextComposable
import com.coaching.paatthya.ui.components.PriceComponent
import com.coaching.paatthya.ui.components.Spacing
import com.coaching.paatthya.ui.components.TopAppBarWithBackButton
import com.coaching.paatthya.ui.components.UrlImageComponent
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.navigation.SystemBackButtonHandler
import com.coaching.paatthya.ui.theme.interVariableFont
import com.coaching.paatthya.domain.model.Batches

@Composable
fun ExploreBatchScreen(batch: Batches){
    Surface {
        BackgroundImage()
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = batch.title)
        },
            containerColor = Color.Transparent) {
            Box(modifier = Modifier.padding(it)) {
                Spacer(modifier = Modifier.height(50.dp).align(Alignment.TopCenter))
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(top=15.dp)
                        .border(1.dp, Color.White)
                ) {
                    Spacing()
                    Box {
                        UrlImageComponent(batch.imgUrl)
                    }
                    Spacing(15.dp)
                    HorizontalDivider()
                    Spacing(15.dp)

                    NormalTextComposable("Course Starting On: ${batch.startDate}")
                    NormalTextComposable("Course Ending On: ${batch.courseCompletionDate}")
                    Spacing(15.dp)
                    HorizontalDivider()
                    Spacing(15.dp)
                    NormalTextComposable(
                        batch.description,
                        startPadding = 15.dp,
                        endPadding = 15.dp,
                        color = Color(0xFFC3C9CC),
                        textAlign = TextAlign.Justify,
                        fontSize = 18.sp,
                        fontFamily = interVariableFont
                    )
                    Spacing(15.dp)
                    HorizontalDivider()
                    Spacing(15.dp)
                    PriceComponent(batch.price, batch.mrp)
                    Spacing(15.dp)
                    HorizontalDivider()
                    Spacing(25.dp)
                    Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                        RoundedButtonComponent(value = "Buy Now") {
//                          onRedirectToPaymentPortal.invoke()
                        }
                    }
                    Spacing(40.dp)
                }
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }

}