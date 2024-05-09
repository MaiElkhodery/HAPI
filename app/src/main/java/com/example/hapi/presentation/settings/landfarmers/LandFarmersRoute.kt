package com.example.hapi.presentation.settings.landfarmers

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "land_farmers"
fun NavGraphBuilder.farmersRoute(navController: NavController) {

    composable(route = ROUTE) {
        LandFarmers(navController = navController)
    }
}

fun NavController.navigateToLandFarmers() {
    navigate(ROUTE)
}