package com.coaching.srit.ui.screens.home.contactus

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coaching.srit.R
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.FAQComposable
import com.coaching.srit.ui.components.IconAndText
import com.coaching.srit.ui.components.Spacing
import com.coaching.srit.ui.components.TopAppBarWithBackButton
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler
import com.coaching.srit.ui.theme.kanitMediumItalic
import com.coaching.srit.ui.viewmodel.ContactUsViewModel

@Composable
fun ContactUsScreen(contactUsViewModel: ContactUsViewModel = viewModel()) {
    Surface {
        val context = LocalContext.current
        BackgroundImage()
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = "Support")
        }, containerColor = Color.Transparent) {
            Column(modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())) {
                Spacing(size = 15.dp)
                Column(modifier = Modifier.border(1.dp, Color.DarkGray)) {
                    Spacing(size = 8.dp)
                    Row {
                        Text(
                            text = "    FAQs ",
                            textAlign = TextAlign.Start,
                            fontSize = 28.sp,
                            color = Color.White,
                            fontFamily = kanitMediumItalic,
                            modifier = Modifier.paddingFromBaseline(4.dp)
                        )
                        Text(
                            text = "   \uD83E\uDD14",
                            fontSize = 32.sp,
                            modifier = Modifier.paddingFromBaseline(2.dp)
                        )
                    }
                    HorizontalDivider()
                    FAQComposable(text = "   Is Test Series Free?") {
                    }
                    FAQComposable(text = "   Are Dpps provided?") {
                    }
                    FAQComposable(text = "   Related to payment") {
                    }
                    FAQComposable(text = "   Are there any fees?") {
                    }
                    FAQComposable(text = "   Will there be a recording?") {
                    }
                    FAQComposable(text = "   How to contact us?") {
                    }
                    Spacing(size = 20.dp)
                }
                Spacing()
                Column(modifier = Modifier.border(1.dp, Color.DarkGray)) {
                    Text(
                        text = "  Reach Out To Us",
                        textAlign = TextAlign.Start,
                        fontSize = 28.sp,
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(
                                R.font.kanit_medium_italic,
                                weight = W600,
                                style = FontStyle.Normal
                            )
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                    HorizontalDivider()
                    IconAndText(
                        text = "9234276712",
                        imageVector = Icons.Outlined.Call,
                        contentDesc = "Call",
                        onClick = {contactUsViewModel.makePhoneCall(context = context, phoneNumber = "9234276712") }
                    )
                    IconAndText(
                        text = "sritcomputerclasses",
                        imageVector = Icons.Outlined.Email,
                        contentDesc = "Email",
                        onClick = { contactUsViewModel.sendMail(context = context, emailAddress = "sritcomputerclasses@gmail.com")}
                    )
                    IconAndText(
                        text = "Patna",
                        imageVector = Icons.Outlined.LocationOn,
                        contentDesc = "Location",
                        onClick = {contactUsViewModel.openLocation(context = context, location = "S.R.I.T COMPUTER CLASSES, PREMCHAND MARG, opposite MOINUL HAQ STADIUM, Rajendra Nagar, Patna, Bihar 800020")}
                    )
                    IconAndText(text = "srit.com", imageVector = Icons.Outlined.Computer, contentDesc = "Website") {
                        contactUsViewModel.openWebsite(context = context, url = "https://sritcomputerclasses.netlify.app/")
                    }
                    Spacing(size = 20.dp)
                }
                Spacing(size = 10.dp)
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}