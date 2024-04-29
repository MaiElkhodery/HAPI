package com.example.hapi.presentation.settings.landowner

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hapi.presentation.main.MainScreen

private const val ROUTE = "landowner_settings"
fun NavGraphBuilder.landownerSettingsRoute(navController: NavController) {

    composable(route = ROUTE) {
        LandownerSettings(navController = navController)
    }
}

fun NavController.navigateToMainScreen(
    role: String
) {
    val route = "main/$role"
    navigate(route)
}