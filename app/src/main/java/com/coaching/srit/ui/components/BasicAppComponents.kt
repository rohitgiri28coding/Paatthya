package com.coaching.srit.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coaching.srit.R
import com.coaching.srit.ui.navigation.Router
import com.coaching.srit.ui.navigation.Screen
import com.coaching.srit.ui.theme.DarkGreen
import com.coaching.srit.ui.theme.Primary
import com.coaching.srit.ui.theme.Shape
import com.coaching.srit.ui.theme.quicksandVariableFont

@Composable
fun LabelComponent(textValue: String){
    Text(
        text = textValue,
        color = Color.LightGray,
        textAlign = TextAlign.Start,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        modifier = Modifier.padding(bottom = 5.dp)
    )
}
@Composable
fun NormalTextComposable(
    textValue: String,
    fontSize: TextUnit = 24.sp,
    color: Color = Color.White,
    textAlign: TextAlign=TextAlign.Center
){
    Text(
        text = textValue,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color =  color,      //colorResource(id= R.color.colorText) other method
        textAlign = textAlign,
        fontFamily = FontFamily(Font(R.font.sedanregular, weight = FontWeight.W400))
    )
}
@Composable
fun HeadingTextComposable(
    textValue: String,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Center
){
    Text(
        text = textValue,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = color,      //colorResource(id= R.color.colorText) other method
        textAlign =  textAlign,
        fontFamily = FontFamily(Font(R.font.sedanregular, weight = FontWeight.W800))

    )
}
@Composable
fun ButtonComponent(
    value: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
){
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent
        ),
        enabled = isEnabled
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(55.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(Color(0xFF0FFD23), Color(0xFF057E11))
                    ), shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.W900,
                color = Color.Black
            )
        }
    }
}
@Composable
fun TransparentButtonComponent(
    value: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
){
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
            .heightIn(48.dp),
        border = BorderStroke(2.dp, Color.White),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent
        ),
        enabled = isEnabled
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(55.dp)
                .background(
                    color = Color.Transparent, shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.background_green),
        contentDescription = stringResource(R.string.background),
        modifier = Modifier.fillMaxSize(1f)
    )
}
@Composable
fun MyTextField(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit,
){
    var textValue by remember{
        mutableStateOf("")
    }
    Column {
            Text(
                text = labelValue,
                color = Color.LightGray,
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shape.small),
                value = textValue,
                onValueChange = {
                    textValue = it
                    onTextSelected(it)
                },
                placeholder = {
                    Text(text = labelValue, color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White,
                    cursorColor = Primary,
                    unfocusedContainerColor = Color(0xFF232623),
                    focusedContainerColor = DarkGreen,
                    focusedIndicatorColor = Primary,
                    focusedLeadingIconColor = Color.White,
                    unfocusedLeadingIconColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(painter = painterResource, contentDescription = null)
                }
            )
        }
    }
@Composable
fun MyPasswordTextField(
    labelValue: String,
    painterResource: Painter,
    onTextSelected: (String) -> Unit
){
    val localFocus = LocalFocusManager.current
    var password by remember{
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    Column {
        Text(
            text = labelValue,
            color = Color.LightGray,
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shape.small),
            value = password,
            onValueChange = {
                password = it
                onTextSelected(it)
            },
            placeholder = {
                Text(text = labelValue, color = Color.White, fontWeight = FontWeight.Bold)
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color.White,
                cursorColor = Primary,
                unfocusedContainerColor = Color(0xFF232623),
                focusedContainerColor = DarkGreen,
                focusedIndicatorColor = Primary,
                focusedLeadingIconColor = Color.White,
                unfocusedLeadingIconColor = Color.White,
                focusedTrailingIconColor = Color.White,
                unfocusedTrailingIconColor = Color.White,
                focusedPlaceholderColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            maxLines = 1,
            singleLine = true,
            keyboardActions = KeyboardActions { localFocus.clearFocus() },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription = null)
            },
            trailingIcon = {
                val iconImage = if (passwordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                val description = if (passwordVisible) {
                    stringResource(R.string.hide_password)
                } else {
                    stringResource(R.string.show_password)
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = iconImage, contentDescription = description)
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}
@Composable
fun UnderlinedTextComposable(textValue: String, onClick:()->Unit){
    Text(
        text = textValue,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .clickable {
                onClick()
            },
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Primary,      //colorResource(id= R.color.colorText) other method
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}
@Composable
fun ClickableTextWithArrowSign(text: String, imageVector: ImageVector = Icons.Default.ChevronRight, fontFamily: Int = R.font.sedanregular, onClick: () -> Unit){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 5.dp, start = 10.dp)
//            .border(1.dp, Color(0xFF3E6412))
            .fillMaxWidth()
            .clickable { onClick.invoke() }
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.rectangle_1),
                contentDescription = "Rectangle"
            )
            Row {
                Text(
                    text = text,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, start = 30.dp),
                    fontFamily = FontFamily(Font(fontFamily, weight = FontWeight.W400))
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "RightArrow",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
@Composable
fun ReviewComposable(img: Int , review: String , name: String, designation: String){
    Box (Modifier.border(2.dp, Color.White)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp)

        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "$name image",
                modifier = Modifier.size(height = 400.dp, width = 420.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            NormalTextComposable(textValue = "                    $name", fontSize = 30.sp)
            Spacer(modifier = Modifier.size(3.dp))
            NormalTextComposable(
                textValue = "                          $designation",
                fontSize = 20.sp
            )
            Box (Modifier.size(width = 360.dp, height = 200.dp)){
                Text(
                    text = review,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 25.dp),
                    color = Color(0xFFCACDD8),
                    fontFamily = FontFamily(Font(R.font.sedanregular, weight = FontWeight.W400))
                )
            }
        }
    }
}
@Composable
fun FAQComposable(text: String, imageVector: ImageVector = Icons.Default.ChevronRight, fontFamily: Int = R.font.kanit_medium_italic, onClick: () -> Unit){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 25.dp, start = 20.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke() }
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.rectangle_2),
                contentDescription = "Rectangle"
            )
            Row (horizontalArrangement = Arrangement.SpaceBetween){
                Text(
                    text = text,
                    color = Color(0xFFCACDD8),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 12.dp, start = 30.dp),
                    fontFamily = FontFamily(Font(fontFamily, weight = FontWeight.W400))
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(end = 65.dp, top = 4.dp)
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "RightArrow",
                        tint = Color(0xFFCACDD8)
                    )
                }
            }
        }
    }
}
@Composable
fun IconAndText(text: String, imageVector: ImageVector, contentDesc: String) {
    Row(
        modifier = Modifier.padding(start = 25.dp, top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDesc,
            tint = Color(0xFFCACDD8),
            modifier = Modifier.padding(top = 7.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            color = Color(0xFFCACDD8),
            fontFamily = FontFamily(
                Font(
                    R.font.kanit_medium_italic,
                    weight = FontWeight.W400,
                    style = FontStyle.Normal
                )
            ),
            modifier = Modifier.paddingFromBaseline(bottom = 10.dp)
        )
    }
}
@Composable
fun DetailedTextComposable(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        modifier = Modifier.padding(10.dp),
        textAlign = TextAlign.Justify,
        color = Color(0xFFCACDD8),
        fontFamily = quicksandVariableFont
    )
}
@Composable
fun NormalHeadingAndDetailedText(heading: String, detail: String){
    Column(modifier = Modifier
        .padding(15.dp)
        .border(1.dp, Color.DarkGray)) {
        Spacer(modifier = Modifier.size(5.dp))
        NormalTextComposable(
            textValue = heading,
            textAlign = TextAlign.Start,
            fontSize = 20.sp
        )
        HorizontalDivider()
        DetailedTextComposable(text = detail)
    }
}
@Composable
fun Spacing(size: Dp = 30.dp){
    Spacer(modifier = Modifier.size(size))
}
@Composable
fun ClickableImageComposable(img: Int, contentDesc: String, onClick: () -> Unit){
    Image(
        painter = painterResource(id = img),
        contentDescription = contentDesc,
        modifier = Modifier.clickable { onClick() }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton(text: String, topAppBarColor: Color = Color.Transparent) {
    TopAppBar(
        title = { HeadingTextComposable(textValue = text) },
        navigationIcon = {
            IconButton(onClick = { Router.navigateTo(Screen.HomeScreen) }) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = "BackButton",
                    tint = Color(0xFFCACDD8),
                    modifier = Modifier
                        .size(35.dp)
                        .border(1.dp, Color.DarkGray)
                )
            }
        },
        colors  = TopAppBarDefaults.topAppBarColors(topAppBarColor)
    )
}