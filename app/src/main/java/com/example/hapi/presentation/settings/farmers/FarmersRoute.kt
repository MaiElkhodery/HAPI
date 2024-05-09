package com.example.hapi.presentation.settings.farmers

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "farmers"
fun NavGraphBuilder.farmersRoute(navController: NavController) {

    composable(route = ROUTE) {
        FarmersList(navController = navController)
    }
}

fun NavController.navigateToFarmers() {
    navigate(ROUTE)
}