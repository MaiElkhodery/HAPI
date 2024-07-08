package com.example.hapi.presentation.settings.data_and_storage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "data_and_storage"
fun NavGraphBuilder.dataAndStorageRoute(navController: NavController) {

    composable(route = ROUTE) {
        DataAndStorage(navController = navController)
    }
}

fun NavController.navigateToDataAndStorage() {
    navigate(ROUTE)
}