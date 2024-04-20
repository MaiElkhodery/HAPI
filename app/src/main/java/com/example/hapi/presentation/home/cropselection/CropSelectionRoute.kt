package com.example.hapi.presentation.home.cropselection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "crop_detection_selection"
fun NavGraphBuilder.cropSelectionRoute(navController: NavController) {
    composable(route = ROUTE) {
        CropSelection(navController)
    }
}

fun NavController.navigateToCropSelection() {
    navigate(ROUTE){
        popUpTo(ROUTE){
            inclusive = true
        }
    }
}