package com.coaching.srit.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.Difference
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Difference
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.coaching.srit.ui.components.BackgroundImage
import com.coaching.srit.ui.components.ClickableTextWithArrowSign
import com.coaching.srit.ui.components.HeadingTextComposable
import com.coaching.srit.ui.components.NormalTextComposable
import com.coaching.srit.ui.screens.home.contactus.ContactUs
import com.coaching.srit.ui.screens.home.result.ResultScreen
import com.coaching.srit.ui.screens.home.termsandconditions.TermsAndConditionsScreen
import com.coaching.srit.ui.screens.home.about.AboutScreen
import com.coaching.srit.ui.screens.home.batches.BatchesScreen
import com.coaching.srit.ui.screens.home.notice.NoticeScreen
import com.coaching.srit.ui.screens.home.study.StudyScreen
import kotlinx.coroutines.launch

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badge: Int? = null,
    val route: String
)
data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val route: String,
    val badgeCount: Int? = null
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val bottomNavItems = listOf(
        BottomNavigationItem(
            title = "Study",
            selectedIcon = Icons.Filled.Book,
            unselectedIcon = Icons.Outlined.Book,
            hasNews = false,
            route = "StudyScreen"
        ),
        BottomNavigationItem(
            title = "Batches",
            selectedIcon = Icons.Filled.Difference,
            unselectedIcon = Icons.Outlined.Difference,
            hasNews = true,
            route = "BatchesScreen"
        ),
        BottomNavigationItem(
            title = "Notice",
            selectedIcon = Icons.AutoMirrored.Filled.Chat,
            unselectedIcon = Icons.AutoMirrored.Outlined.Chat,
            hasNews = false,
            route = "NoticeScreen",
            badgeCount = 20
        ),
        BottomNavigationItem(
            title = "About",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            hasNews = false,
            route = "AboutScreen"
        ),
    )
    val navDrawerItems = listOf(
        NavigationItem(
            title = "Our Results",
            selectedIcon = Icons.Filled.Book,
            unselectedIcon = Icons.Outlined.Book,
            badge = 30,
            route = "ResultScreen"
        ),
        NavigationItem(
            title = "Contact Us",
            selectedIcon = Icons.Filled.Call,
            unselectedIcon = Icons.Outlined.Call,
            route = "ContactUsScreen"
        ),
        NavigationItem(
            title = "Terms & Conditions",
            selectedIcon = Icons.Filled.AccountBalanceWallet,
            unselectedIcon = Icons.Outlined.AccountBalanceWallet,
            route = "TermsAndConditionsScreen"
        )

    )
    val currentScreen = remember {
        mutableStateOf("StudyScreen")
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var bottomBarSelectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    var navigationDrawerSelectedItemIndex by rememberSaveable {
        mutableIntStateOf(5)
    }
    Box {
        BackgroundImage()
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = Color.Black,
                    drawerContentColor = Color.Black
                ) {
                    Spacer(modifier = Modifier.size(20.dp))
                    HeadingTextComposable(textValue = "S.R.I.T")
                    Spacer(modifier = Modifier.size(40.dp))
                    Divider()
                    Spacer(modifier = Modifier.size(20.dp))
                    navDrawerItems.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                NormalTextComposable(
                                    textValue = item.title,
                                    textAlign = TextAlign.Start
                                )
                            },
                            selected = index == navigationDrawerSelectedItemIndex,
                            colors = NavigationDrawerItemDefaults.colors(
                                Color.DarkGray,
                                Color.Transparent
                            ),
                            onClick = {
                                navigationDrawerSelectedItemIndex = index
                                currentScreen.value = item.route
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == navigationDrawerSelectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            badge = {
                                if (item.badge != null) {
                                    Text(text = item.badge.toString(), color = Color.White)
                                }
                            }
                        )
                    }
                }
            },
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            ClickableTextWithArrowSign(text = "Android Development", onClick = {})
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Dehaze,
                                    contentDescription = "Navigation Drawer",
                                    tint = Color.White,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                    )

                },
                bottomBar = {
                    NavigationBar(containerColor = Color.Transparent) {
                        bottomNavItems.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = bottomBarSelectedItemIndex == index,
                                onClick = {
                                    bottomBarSelectedItemIndex = index
                                    navigationDrawerSelectedItemIndex = 5
                                    currentScreen.value = item.route
                                },
                                label = {
                                    Text(text = item.title, color = Color.White)
                                },

                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (item.badgeCount != null) {
                                                Badge {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            } else if (item.hasNews) {
                                                Badge()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (index == bottomBarSelectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                }
                            )
                        }
                    }

                },
                containerColor = Color.Transparent
            ) { contentPadding ->
                Box(modifier = Modifier.padding(contentPadding)) {
                    when (currentScreen.value) {
                        "StudyScreen" -> {
                            StudyScreen()
                        }

                        "AboutScreen" -> {
                            AboutScreen()
                        }

                        "BatchesScreen" -> {
                            BatchesScreen()
                        }

                        "NoticeScreen" -> {
                            NoticeScreen()
                        }

                        "NavigationDrawer" -> {
                            HomeScreen()
                        }

                        "ResultScreen" -> {
                            ResultScreen()
                        }

                        "ContactUsScreen" -> {
                            ContactUs()
                        }

                        "TermsAndConditionsScreen" -> {
                            TermsAndConditionsScreen()
                        }
                    }
                }
            }
        }
    }
}