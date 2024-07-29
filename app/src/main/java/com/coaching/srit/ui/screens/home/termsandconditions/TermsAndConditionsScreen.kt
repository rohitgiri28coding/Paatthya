package com.coaching.srit.ui.screens.home.termsandconditions

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.NormalHeadingAndDetailedText
import com.coaching.srit.ui.components.TopAppBarWithBackButton
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Surface {
        BackgroundImage()
        Scaffold(topBar = {
            TopAppBarWithBackButton(text = "Terms and Conditions")
        },
            containerColor = Color.Transparent) {
            Column(modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())) {
                NormalHeadingAndDetailedText(
                    heading = "  Acceptance of Terms",
                    detail = "By using the S.R.I.T (Shubham Raj Info Tech) app, you agree to be bound by these terms and conditions. If you do not agree to these terms, please do not use the app."
                )
                NormalHeadingAndDetailedText(
                    heading = "  Privacy Policy",
                    detail = "Your use of the S.R.I.T app signifies your acceptance of the S.R.I.T Privacy Policy, which is incorporated into these terms. The Privacy Policy outlines how we collect, use, and protect your personal information.\n" +
                            "\n" +
                            "You also agree to the privacy policy available on our website: S.R.I.T Privacy Policy."
                )
                NormalHeadingAndDetailedText(
                    heading = "  User Conduct",
                    detail = "By using the S.R.I.T app, you agree to:\n" +
                            "\n" +
                            "Use the app in compliance with all applicable laws and regulations.\n" +
                            "Provide accurate and truthful information when creating an account or providing any information to us.\n" +
                            "Not engage in any activity that could harm or disrupt the app or its users."
                )
                NormalHeadingAndDetailedText(
                    heading = "  Intellectual Property",
                    detail = "All content provided through the S.R.I.T app, including but not limited to text, graphics, logos, and software, is the property of S.R.I.T and is protected by intellectual property laws. You may not reproduce, distribute, or create derivative works from any content without explicit permission from S.R.I.T."
                )
                NormalHeadingAndDetailedText(
                    heading = "  Limitation of Liability",
                    detail = "S.R.I.T shall not be liable for any direct, indirect, incidental, special, or consequential damages resulting from the use or inability to use the app. This includes but is not limited to damages for loss of profits, data, or other intangible losses."
                )
                NormalHeadingAndDetailedText(
                    heading = "  Changes to Terms",
                    detail = "S.R.I.T reserves the right to modify these terms and conditions at any time. Your continued use of the app after any changes signifies your acceptance of the new terms."
                )
                NormalHeadingAndDetailedText(
                    heading = "  Contact Information",
                    detail = "For any questions or concerns regarding these terms and conditions, please contact us at [https://sritcomputerclasses.netlify.app/].\n" +
                            "\n" +
                            "By using the S.R.I.T app, you acknowledge that you have read, understood, and agree to these terms and conditions."
                )
            }
        }
    }
    SystemBackButtonHandler {
        Router.navigateTo(Screen.HomeScreen)
    }
}
fun openWebsite(context: Context, url: String){
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}