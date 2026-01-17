package com.jaydeep.zonerun.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jaydeep.zonerun.auth.AuthViewModel
import com.jaydeep.zonerun.ui.auth.LoginScreen
import com.jaydeep.zonerun.ui.auth.SignupScreen

@Composable
fun AuthNavGraph(
    authViewModel: AuthViewModel,
    onAuthSuccess: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onSuccess = onAuthSuccess,
                onSignupClick = {
                    navController.navigate("signup")
                }
            )
        }

        composable("signup") {
            SignupScreen(
                viewModel = authViewModel,
                onSuccess = onAuthSuccess,
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
