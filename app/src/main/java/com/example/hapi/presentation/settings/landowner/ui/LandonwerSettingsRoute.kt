package com.example.hapi.presentation.settings.landowner.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "landowner_settings"
fun NavGraphBuilder.landownerSettingsRoute(navController: NavController) {

    composable(route = ROUTE) {
        LandownerSettings(navController = navController)
    }
}

fun NavController.navigateToLandownerSettings() {
    navigate(ROUTE)
}