package com.example.hapi.presentation.detection.guest_cropselection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "guest_crop_detection_selection"
fun NavGraphBuilder.guestCropSelectionRoute(navController: NavController) {
    composable(route = ROUTE) {
        GuestCropSelection(navController)
    }
}

fun NavController.navigateToGuestCropSelection() {
    navigate(ROUTE){
        popUpTo(ROUTE){
            inclusive = false
        }
    }
}