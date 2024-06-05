package com.example.hapi.presentation.settings.farmerslist.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "farmers_list"
fun NavGraphBuilder.farmersRoute(navController: NavController) {

    composable(route = ROUTE) {
        FarmersListItems(navController = navController)
    }
}

fun NavController.navigateToLandFarmers() {
    navigate(ROUTE)
}