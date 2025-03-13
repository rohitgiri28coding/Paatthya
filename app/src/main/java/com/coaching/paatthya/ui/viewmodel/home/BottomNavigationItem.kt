package com.coaching.paatthya.ui.viewmodel.home

import androidx.compose.ui.graphics.vector.ImageVector
import com.coaching.paatthya.ui.navigation.HomeScreen

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    var hasNews: Boolean,
    val screenRoute: HomeScreen,
    var badgeCount: Int? = null
)