package com.example.hapi.presentation.home.detectionhistory.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "detection_history"
fun NavGraphBuilder.detectionHistoryRoute(navController: NavController) {

    composable(route = ROUTE) {
        DetectionHistory(navController)
    }
}
fun NavController.navigateToDetectionHistory(){
    navigate(ROUTE)
}