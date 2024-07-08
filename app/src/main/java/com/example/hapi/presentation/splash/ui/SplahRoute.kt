package com.example.hapi.presentation.splash.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "splash"
fun NavGraphBuilder.splashOneRoute(navController: NavController) {
    composable(route = ROUTE) {
        Splash(navController)
    }
}