package com.jaydeep.zonerun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaydeep.zonerun.auth.AuthViewModel
import com.jaydeep.zonerun.navigation.AuthNavGraph
import com.jaydeep.zonerun.ui.auth.SignupScreen
import com.jaydeep.zonerun.ui.theme.ZoneRunTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val authViewModel: AuthViewModel = viewModel()

            if (authViewModel.state.isLoggedIn) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    demo()
                }
            } else {
                AuthNavGraph(
                    authViewModel = authViewModel,
                    onAuthSuccess = {
                        // this will auto recompose and go to main app
                    }
                )
            }
        }


    }
}


@Composable
fun demo(modifier: Modifier = Modifier) {
    val authViewModel: AuthViewModel = viewModel()
    Column(modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Welcome to ZoneRun!")
        Button(onClick = { authViewModel.logout() },
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth()) {
            Text(text = "Logout")
        }
    }
}

