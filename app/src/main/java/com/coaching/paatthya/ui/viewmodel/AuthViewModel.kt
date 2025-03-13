package com.coaching.paatthya.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.coaching.paatthya.ui.navigation.Router
import com.coaching.paatthya.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    auth: FirebaseAuth
) : ViewModel() {
    init {
        if (auth.currentUser != null){
            Router.navigateTo(Screen.HomeScreen)
        }
    }
}