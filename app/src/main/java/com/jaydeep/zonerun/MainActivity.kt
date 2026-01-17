package com.jaydeep.zonerun

import ZoneRunNavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaydeep.zonerun.auth.AuthViewModel
import com.jaydeep.zonerun.navigation.AuthNavGraph


import com.jaydeep.zonerun.ui.theme.ZoneRunTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ZoneRunTheme {

                val authViewModel: AuthViewModel = viewModel()
                val authState = authViewModel.state

                if (authState.isLoggedIn) {
                    // ✅ Main app navigation
                    ZoneRunNavGraph(
                        onLogout = { authViewModel.logout() }
                    )
                } else {
                    // ✅ Authentication navigation
                    AuthNavGraph(
                        authViewModel = authViewModel,
                        onAuthSuccess = {
                            // nothing needed here,
                            // recomposition will auto switch UI
                        }
                    )
                }
            }
        }
    }
}
