package com.example.hapi.presentation.signup.landownersignup.finalcrop

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hapi.data.model.Crop
import com.example.hapi.data.model.CropType

private const val ROUTE = "final_crop/{crop_name}/{crop_imageId}"
fun NavGraphBuilder.finalCropRoute(navController: NavController) {
    composable(route = ROUTE) { backEntryStack ->
        FinalCropScreen(
            navController = navController, crop = Crop(
                type = CropType.valueOf(backEntryStack.arguments?.getString("cropName") ?: ""),
                imageId = backEntryStack.arguments?.getInt("cropId") ?: 0
            )
        )
    }
}

fun NavController.navToFinalCropScreen(crop: Crop) {
    navigate("$ROUTE/${crop.type.name}/${crop.imageId}")
}