package com.coaching.paatthya.ui.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Difference
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.BrowseGallery
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Difference
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coaching.paatthya.domain.repository.Result
import com.coaching.paatthya.domain.usecase.SignOutUseCase
import com.coaching.paatthya.ui.navigation.HomeScreen
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.coaching.paatthya.domain.model.BottomNavigationItem
import com.coaching.paatthya.domain.model.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val signOutUseCase: SignOutUseCase): ViewModel(){
    fun signOut() {
        viewModelScope.launch {
            when(signOutUseCase.executeSignOut()){
                is Result.Error -> signOut()
                is Result.Success -> Router.navigateTo(Screen.WelcomeScreen)
            }
        }
    }
    val navDrawerItems = listOf(
        NavigationItem(
            title = "Our Results",
            selectedIcon = Icons.Filled.Book,
            unselectedIcon = Icons.Outlined.Book,
            badge = 3,
            screenRoute = Screen.ResultScreen
        ),
        NavigationItem(
            title = "Contact Us",
            selectedIcon = Icons.Filled.Call,
            unselectedIcon = Icons.Outlined.Call,
            screenRoute = Screen.ContactUsScreen
        ),
        NavigationItem(
            title = "Terms & Conditions",
            selectedIcon = Icons.Filled.AccountBalanceWallet,
            unselectedIcon = Icons.Outlined.AccountBalanceWallet,
            screenRoute = Screen.TermsAndConditionsScreen
        ),
        NavigationItem(
            title = "Gallery",
            selectedIcon = Icons.Filled.BrowseGallery,
            unselectedIcon = Icons.Outlined.BrowseGallery,
            screenRoute = Screen.GalleryScreen
        )
    )
    val bottomNavItems = listOf(
        BottomNavigationItem(
            title = "Study",
            selectedIcon = Icons.Filled.Book,
            unselectedIcon = Icons.Outlined.Book,
            hasNews = false,
            screenRoute = HomeScreen.StudyScreen
        ),
        BottomNavigationItem(
            title = "Batches",
            selectedIcon = Icons.Filled.Difference,
            unselectedIcon = Icons.Outlined.Difference,
            hasNews = true,
            screenRoute = HomeScreen.ViewAllBatchesScreen
        ),
        BottomNavigationItem(
            title = "Notice",
            selectedIcon = Icons.AutoMirrored.Filled.Chat,
            unselectedIcon = Icons.AutoMirrored.Outlined.Chat,
            hasNews = false,
            screenRoute = HomeScreen.NoticeScreen,
            badgeCount = 3
        ),
        BottomNavigationItem(
            title = "About",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            hasNews = false,
            screenRoute = HomeScreen.AboutScreen
        )
    )
    var bottomBarSelectedItemIndex = mutableIntStateOf(0)
        private set

    var navigationDrawerSelectedItemIndex = mutableIntStateOf(5)
        private set

    fun updateBottomBarIndex(index: Int) {
        bottomBarSelectedItemIndex.intValue = index
    }
    fun updateNavigationDrawerIndex(index: Int) {
        navigationDrawerSelectedItemIndex.intValue = index
    }
    fun updateNavBadgeCount(index: Int, count: Int) {
        navDrawerItems[index].badge = count
    }
    fun updateBottomNavBadgeCount(index: Int, count: Int) {
        bottomNavItems[index].badgeCount = count
    }
    fun updateBottomNavNewsCount(index: Int) {
        bottomNavItems[index].hasNews = !bottomNavItems[index].hasNews
    }
}