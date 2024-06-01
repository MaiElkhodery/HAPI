package com.example.hapi.presentation.settings.farmer.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "farmer_settings"
fun NavGraphBuilder.farmerSettingsRoute(navController: NavController) {

    composable(route = ROUTE) {
        FarmerSettings(navController = navController)
    }
}

fun NavController.navigateToFarmerSettings() {
    navigate(ROUTE)
}