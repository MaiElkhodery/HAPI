package com.example.hapi.presentation.home.diseasedetection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hapi.util.Crop

private const val ROUTE = "image_capture/{crop}"
fun NavGraphBuilder.imageCaptureRoute(navController: NavController) {
    composable(route = ROUTE) {
        val crop = Crop.valueOf(it.arguments?.getString("crop")!!)
        ImageCapture(navController, crop)
    }
}

fun NavController.navigateToImageCapture(
    crop: String
) {
    val route = "image_capture/$crop"
    navigate(route)
}