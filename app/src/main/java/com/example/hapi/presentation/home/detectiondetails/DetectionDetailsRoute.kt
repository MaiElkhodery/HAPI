package com.example.hapi.presentation.home.detectiondetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "detection_details/{detectionId}"
fun NavGraphBuilder.detectionDetailsRoute(navController: NavController) {

    composable(route = ROUTE) {
        val detectionId = it.arguments?.getInt("detectionId")
        DetectionDetails(navController, detectionId!!)
    }
}

fun NavController.navigateToDetectionDetails(
    detectionId: Int
) {
    val route = "detection_details/$detectionId"
    navigate(route) {
        popUpTo(ROUTE) {
            inclusive = true
        }
    }
}