package com.coaching.paatthya.ui.components

import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.coaching.paatthya.R
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.ui.theme.DarkGreen
import com.coaching.paatthya.ui.theme.Primary
import com.coaching.paatthya.ui.theme.Shape
import com.coaching.paatthya.ui.theme.interVariableFont
import com.coaching.paatthya.ui.theme.kanit_light
import com.coaching.paatthya.ui.theme.quicksandVariableFont
import com.coaching.paatthya.ui.theme.sedanRegular
import com.coaching.paatthya.domain.model.Batches
import java.text.NumberFormat
import java.util.Locale

@Composable
fun NormalTextComposable(
    textValue: String,
    fontSize: TextUnit = 24.sp,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Center,
    startPadding: Dp = 0.dp,
    endPadding: Dp = 0.dp,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily(
        Font(
            resId = R.font.sedanregular
        )
    )
){
    Text(
        text = textValue,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = startPadding,
                end = endPadding,
                top = topPadding,
                bottom = bottomPadding
            )
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = FontStyle.Normal
        ),
        color =  color,      //colorResource(id= R.color.colorText) other method
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}
@Composable
fun ClickableLoginTextComponent(text: String, clickableText: String, onClick: () -> Unit) {
    val annotatedString = buildAnnotatedString {

        val startIndex = text.indexOf(clickableText)
        val endIndex = startIndex + clickableText.length

        append(text)

        addStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            start = 0,
            end = text.length
        )

        addStyle(
            style = SpanStyle(
                color = Color(0xFF60D726),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            start = startIndex,
            end = endIndex
        )

        addLink(
            LinkAnnotation.Clickable(tag = clickableText.lowercase()) { onClick() },
            start = startIndex,
            end = endIndex
        )
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        BasicText(
            text = annotatedString,
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = interVariableFont
            )
        )
    }
}

@Composable
fun HeadingTextComposable(
    textValue: String,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Center,
    textUnit: TextUnit = 30.sp
){
    Text(
        text = textValue,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = textUnit,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = color,      //colorResource(id= R.color.colorText) other method
        textAlign =  textAlign,
        fontFamily = FontFamily(Font(R.font.sedanregular, weight = FontWeight.W800))

    )
}
@Composable
fun RoundedButtonComponent(
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
                        listOf(Color(0xFF1DE22D), Color(0xFF13751D))
                    ), shape = RoundedCornerShape(50.dp)
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
fun ButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    icon: Int,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .height(55.dp), // Only height here
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(Color(0xFF1DE22D), Color(0xFF13751D)),
                    ),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp)
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = text,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = text,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.sedanregular, weight = FontWeight.W500))
                )
            }
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
        contentScale = ContentScale.FillBounds,
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
            .fillMaxWidth()
            .clickable { onClick.invoke() }
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.rectangle_1),
                contentDescription = "Rectangle",
                modifier = Modifier.fillMaxWidth().padding(end = 45.dp)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = text,
                    color = Color.White,
                    modifier = Modifier.padding(top = 5.dp, bottom=5.dp, end = 45.dp),
                    fontFamily = FontFamily(Font(fontFamily, weight = FontWeight.W400))
                )

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
                .align(Alignment.Center)
        ) {
            Spacing()
            Image(
                painter = painterResource(id = img),
                contentDescription = "$name image",
                modifier = Modifier.size(height = 400.dp, width = 420.dp)
            )
            Spacing()
            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                NormalTextComposable(
                    textValue = name,
                    fontSize = 30.sp,
                    startPadding = 5.dp
                )
                Spacing(size = 3.dp)
                NormalTextComposable(
                    textValue = designation,
                    fontSize = 20.sp,
                )
            }
            Box (
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(width = 360.dp, height = 200.dp)
                    .verticalScroll(rememberScrollState())){
                NormalTextComposable(
                    textValue = review,
                    fontSize = 18.sp,
                    color = Color(0xFFCACDD8),
                )
            }
        }
    }
}
@Composable
fun FAQComposable(
    text: String,
    detail: String="No Answer Available",
    imageVector: ImageVector = Icons.Default.ChevronRight,
    fontFamily: Int = R.font.kanit_medium_italic,
    onClick: () -> Unit
){
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
fun IconAndText(text: String, imageVector: ImageVector, contentDesc: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(start = 25.dp, top = 20.dp).clickable { onClick.invoke() },
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
        Spacing(size = 5.dp)
        NormalTextComposable(
            textValue = heading,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
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
fun ClickableImageComposable(img: Int, contentDesc: String, padding: Dp=0.dp, onClick: () -> Unit){
    Image(
        painter = painterResource(id = img),
        contentDescription = contentDesc,
        modifier = Modifier
            .clickable { onClick() }
            .padding(padding)
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
@Composable
fun GenerateFeedComponent(name: String, timeStamp: String, messages: String, image: Int) {
    Column {
        Row(modifier = Modifier.padding(start = 8.dp)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .border(2.dp, Color.DarkGray, CircleShape)
                    .size(50.dp)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Spacing(size = 2.dp)
                NameAndTimeStamp(name = name, timeStamp = timeStamp)
                Spacing(size = 4.dp)
                TextInRoundedBox(message = messages)

            }
        }
    }
}
@Composable
fun NameAndTimeStamp(name: String, timeStamp: String){
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )){
            append("$name   ")
        }
        withStyle(style = SpanStyle(
            color = Color.White,
            fontSize = 13.sp,
        )){
            append(timeStamp)
        }
    }, fontFamily = kanit_light)
}
@Composable
fun TextInRoundedBox(message: String) {
    var isExpanded by remember { mutableStateOf(false) }
    Column (modifier = Modifier.padding(end = 20.dp)){
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        bottomEnd = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp
                    )
                )
                .background(Color(0xFF275836))
                .padding(16.dp)
        ) {
            Text(
                text = message,
                color = Color.White,
                fontFamily = interVariableFont,
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .animateContentSize()
                    .clickable { isExpanded = !isExpanded },
                maxLines = if (isExpanded) Int.MAX_VALUE else 5
            )
        }
        Spacing(size = 10.dp)
    }
}
@Composable
fun TextDivider(text: String) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}
@Composable
fun GalleryComponent(img: Int, title: String, onClick: () -> Unit){
    Box(
        modifier = Modifier
            .clickable { onClick.invoke() }
            .clip(
                RoundedCornerShape(
                    bottomEnd = 25.dp,
                    topEnd = 25.dp,
                    bottomStart = 25.dp
                )
            )
            .border(
                width = 2.dp,
                color = Color.White,
                shape = RoundedCornerShape(
                    bottomEnd = 25.dp,
                    topEnd = 25.dp,
                    bottomStart = 25.dp
                )
            )
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
        )
        Text(
            text = title,
            fontFamily = sedanRegular,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 5.dp)
        )
    }

}
@Composable
fun GalleryRowComponent(img1: Int, img2: Int, title1: String, title2: String, onClick1: () -> Unit, onClick2: () -> Unit) {
    Spacing()
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        GalleryComponent(img = img1, title = title1){
            onClick1.invoke()
        }
        if (img2 != 0) {
            GalleryComponent(img = img2, title = title2) {
                onClick2.invoke()
            }
        }
    }
}

@Composable
fun SwipeableBatchComponent(batches: List<Batches>) {
    val images = batches.map { it.imgUrl }
    val pagerState = rememberPagerState(pageCount = { images.size })
    HorizontalPager(state = pagerState, key = {it.hashCode()}) { index->
        Box {
            UrlImageComponent(images[index])
            Row (modifier = Modifier.align(Alignment.BottomCenter)){
                images.forEachIndexed { index, _ ->
                    IndicatorComponent(active = pagerState.currentPage == index)
                    if (index < images.size - 1){
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun UrlImageComponent(imgUrl: String) {
    AsyncImage(
        model = imgUrl,
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = Modifier.fillMaxWidth(),
        placeholder = painterResource(id = R.drawable.password), // Optional: Add a placeholder
    )
}


@Composable
fun IndicatorComponent(active: Boolean){
    val color  = if (active) Color(0xFFAC2222) else Color.White
    Box(modifier = Modifier
        .clip(CircleShape)
        .background(color)
        .size(10.dp))
}

@Composable
fun PricedBatchComponent(batch: Batches, onExploreButtonClick: () -> Unit, onRedirectToPaymentPortal: () -> Unit){
    Column (modifier = Modifier
        .fillMaxWidth()
        .border(2.dp, Color.White)
        .padding(8.dp)){
        Column(modifier = Modifier
            .clickable { onExploreButtonClick.invoke() }) {
            Spacing(10.dp)
            NormalTextComposable(
                textValue = batch.title,
                fontSize = 20.sp,
                )
            HorizontalDivider()
            Spacing(3.dp)
            Box {
                UrlImageComponent(batch.imgUrl)
            }
            Spacing(3.dp)
            HorizontalDivider()
            Spacing(size = 15.dp)
            PriceComponent(batch.price, batch.mrp)
        }
        Spacing(size = 15.dp)
        HorizontalDivider()
        Spacing(10.dp)
        Column {
            TransparentButtonComponent(value = "Explore") {
                onExploreButtonClick.invoke()
            }
            Spacing(size = 10.dp)
            RoundedButtonComponent(value = "Buy Now"){
                onRedirectToPaymentPortal.invoke()
            }
        }
        Spacing(size = 10.dp)
    }
}

@Composable
fun MyBatchComponent(batch: Batches, onExploreButtonClick: () -> Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .border(2.dp, Color.White)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .clickable { onExploreButtonClick.invoke() }) {
                Spacing(10.dp)
                NormalTextComposable(
                    textValue = batch.title,
                    fontSize = 20.sp,
                )
                HorizontalDivider()
                Spacing(3.dp)
                Box {
                    UrlImageComponent(batch.imgUrl)
                }
            }
            Spacing(size = 15.dp)
            HorizontalDivider()
            Spacing(10.dp)
            RoundedButtonComponent (value = "Go to Study Material") {
                onExploreButtonClick.invoke()
            }
            Spacing(size = 10.dp)
        }
    }
}

@Composable
fun PriceComponent(price: Double, mrp: Double) {
    val priceText = formatNumber(price)
    val mrpText = formatNumber(mrp)
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = buildAnnotatedString {
                append("                     ")
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("₹${priceText}/-")
                }
                append("   ")
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        textDecoration = TextDecoration.LineThrough,
                        fontSize = 20.sp
                    )
                ) {
                    append("₹$mrpText/-")
                }
            },
            fontFamily = sedanRegular,
            textAlign = TextAlign.Center
        )
    }
}
private fun formatNumber(number: Double): String {
    return NumberFormat.getInstance(Locale("en", "IN")).format(number)
}
@Composable
fun GoogleSignInButton(openGoogleSignIn: () -> Unit) {
    Spacing()
    TextDivider(text = stringResource(R.string.or_connect_using))
    Spacing()
    Button(
        onClick = {
            openGoogleSignIn.invoke()
        },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 55.dp)
            .border(2.dp, Color(0xFF343D28), RoundedCornerShape(24.dp))
    ) {
        ClickableImageComposable(
            img = R.drawable.google_logo,
            contentDesc = stringResource(R.string.google),
            padding = 8.dp
        )
        {
            openGoogleSignIn.invoke()
        }
        Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Spacing(7.dp)
            NormalTextComposable(
                textValue = stringResource(R.string.sign_in_with_google),
                fontSize = 20.sp
            )
        }
    }
}
@Composable
fun MessageBox(onTextSelected: (String) -> Unit, onSend: () -> Unit) {
    var message by remember { mutableStateOf("") }
    Row {
        OutlinedTextField(
            value = message,
            onValueChange = {
                message = it
                onTextSelected(it)
            },
            placeholder = {
                Text(text = "Enter message", color = Color.White)
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFF13751D),
                focusedBorderColor = Color(0xFF1DE22D),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.Companion
                .weight(1f)
                .padding(6.dp)
                .height(56.dp),
        )
        Box(
            modifier = Modifier
                .heightIn(32.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(Color(0xFF1DE22D), Color(0xFF13751D))
                    ),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Button(
                onClick = {
                    onSend.invoke()
                    message = ""
                },
                enabled = message.trim().isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                )
            ) {
                Text(text = "Send", color = Color.White)
            }
        }
    }
}