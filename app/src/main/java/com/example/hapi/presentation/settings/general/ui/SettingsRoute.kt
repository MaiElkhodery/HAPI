package com.example.hapi.presentation.settings.general.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "settings"
fun NavGraphBuilder.settingsRoute(navController: NavController) {

    composable(route = ROUTE) {
        Settings(navController = navController)
    }
}

fun NavController.navigateToSettings() {
    navigate(ROUTE)
}