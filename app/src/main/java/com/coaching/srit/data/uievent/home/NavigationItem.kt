package com.coaching.srit.data.uievent.home

import androidx.compose.ui.graphics.vector.ImageVector
import com.coaching.srit.ui.navigation.Screen

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    var badge: Int? = null,
    val screenRoute: Screen
)