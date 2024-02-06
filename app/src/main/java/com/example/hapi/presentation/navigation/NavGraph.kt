package com.example.hapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hapi.presentation.signup.progress.progressRoute
import com.example.hapi.presentation.splash.splashOneRoute

@Composable
fun NavGraph(navController: NavHostController) {
    val startRoute = "splash"
    NavHost(navController = navController, startDestination = startRoute) {
        splashOneRoute(navController)
        progressRoute(navController)
    }
}