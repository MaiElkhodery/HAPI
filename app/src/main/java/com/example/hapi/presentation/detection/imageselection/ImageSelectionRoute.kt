package com.example.hapi.presentation.detection.imageselection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hapi.util.Crop

private const val ROUTE = "image_selection/{crop}"
fun NavGraphBuilder.imageCaptureRoute(navController: NavController) {
    composable(route = ROUTE) {
        val crop = Crop.valueOf(it.arguments?.getString("crop")!!)
        ImageSelection(navController, crop)
    }
}

fun NavController.navigateToImageSelection(
    crop: String
) {
    val route = "image_selection/$crop"
    navigate(route)
}