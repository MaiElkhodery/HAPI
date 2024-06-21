package com.example.hapi.presentation.settings.farmers.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "farmers"
fun NavGraphBuilder.farmersRoute(navController: NavController) {

    composable(route = ROUTE) {
        Farmers(navController = navController)
    }
}

fun NavController.navigateToFarmers() {
    navigate(ROUTE)
}