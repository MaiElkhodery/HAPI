package com.example.hapi.presentation.settings.about

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "about_us"
fun NavGraphBuilder.aboutUsRoute(navController: NavController) {

    composable(route = ROUTE) {
        AboutUs(navController = navController)
    }
}

fun NavController.navigateToAboutUs() {
    navigate(ROUTE)
}