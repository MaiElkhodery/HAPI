package com.example.hapi.presentation.auth.signup.landownersignup.detection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "crop_detection"
fun NavGraphBuilder.cropDetectionRoute(navController: NavController) {
    composable(route = ROUTE) {
        CropDetectionScreen(navController)
    }
}

fun NavController.navToCropDetection() {
    navigate(ROUTE)
}