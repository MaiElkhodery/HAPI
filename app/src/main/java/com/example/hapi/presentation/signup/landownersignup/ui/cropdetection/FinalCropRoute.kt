package com.example.hapi.presentation.signup.landownersignup.ui.cropdetection

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hapi.data.model.Crop

private const val ROUTE = "final_crop/{crop_name}/{crop_imageId}"
fun NavGraphBuilder.finalCropRoute(navController: NavController) {
    composable(route = ROUTE) { backEntryStack ->
        FinalCropScreen(
            navController = navController, crop = Crop(
                name = backEntryStack.arguments?.getString("cropName") ?: "",
                image = backEntryStack.arguments?.getInt("cropId") ?: 0
            )
        )
    }
}

fun NavController.navToFinalCropScreen(crop: Crop) {
    navigate("${ROUTE}/${crop.name}/${crop.image}")
}