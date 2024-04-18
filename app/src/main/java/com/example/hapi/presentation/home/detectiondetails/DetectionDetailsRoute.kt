package com.example.hapi.presentation.home.detectiondetails

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "detection_details/{localId}/{remoteId}"
fun NavGraphBuilder.detectionDetailsRoute(navController: NavController) {

    composable(route = ROUTE) { backStackEntry ->
        val localId = backStackEntry.arguments?.getString("localId")!!
        val remoteId = backStackEntry.arguments?.getString("remoteId")!!
        DetectionDetails(
            navController = navController,
            localId = localId,
            remoteId = remoteId
        )
    }
}

fun NavController.navigateToDetectionDetails(
    localId: String,
    remoteId: String = ""
) {
    val route = "detection_details/$localId/$remoteId"
    navigate(route)
}