package com.example.hapi.presentation.home.detectiondetails

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "detection_details/{id}/{isCurrentDetection}"
fun NavGraphBuilder.detectionDetailsRoute(navController: NavController) {

    composable(route = ROUTE) { backStackEntry ->
        val id = backStackEntry.arguments?.getString("id")!!.toInt()
        val isCurrentDetection = backStackEntry.arguments?.getString("isCurrentDetection").toBoolean()
        Log.d("DetectionDetailsRoute", "id: $id, isCurrentDetection: $isCurrentDetection")
        Log.d("DetectionDetailsRoute", backStackEntry.arguments.toString())
        DetectionDetails(
            navController = navController,
            id=id,
            isCurrentDetection = isCurrentDetection
        )
    }
}

fun NavController.navigateToDetectionDetails(
    id:String,
    isCurrentDetection: String = "false"
) {
    val route = "detection_details/$id/$isCurrentDetection"
    navigate(route)
}