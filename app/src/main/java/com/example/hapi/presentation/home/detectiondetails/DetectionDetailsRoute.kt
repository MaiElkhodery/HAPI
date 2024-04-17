package com.example.hapi.presentation.home.detectiondetails

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "detection_details/{detectionId}/{isLocal}"
fun NavGraphBuilder.detectionDetailsRoute(navController: NavController) {

    composable(route = ROUTE) { backStackEntry ->
        val detectionId = backStackEntry.arguments?.getString("detectionId")!!.toInt()
        val isLocal = backStackEntry.arguments?.getString("isLocal")!!.toBoolean()
        Log.d("DetectionDetailsRoute", backStackEntry.arguments.toString())
        DetectionDetails(
            navController = navController,
            detectionId = detectionId,
            local = isLocal
        )
    }
}

fun NavController.navigateToDetectionDetails(
    detectionId: String,
    isLocal: String = "false"
) {
    val route = "detection_details/$detectionId/$isLocal"
    navigate(route)
}