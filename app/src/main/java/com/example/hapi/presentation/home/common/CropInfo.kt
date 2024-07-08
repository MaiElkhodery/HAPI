package com.example.hapi.presentation.home.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.hapi.R
import com.example.hapi.util.Crop

@Composable
fun getCropIcon(
    crop: String
): Int {
    return when (crop) {
        stringResource(id = R.string.tomato) -> R.drawable.tomato
        stringResource(id = R.string.potato) -> R.drawable.potato
        stringResource(id = R.string.wheat) -> R.drawable.wheat
        stringResource(id = R.string.corn) -> R.drawable.corn
        stringResource(id = R.string.cotton) -> R.drawable.cotton
        stringResource(id = R.string.apple) -> R.drawable.apple
        stringResource(id = R.string.sugarcane) -> R.drawable.sugar_cane
        else -> 0
    }
}

fun getCropName(crop: String): Int {
    return when (crop) {
        Crop.TOMATO.name -> R.string.tomato
        Crop.POTATO.name -> R.string.potato
        Crop.WHEAT.name -> R.string.wheat
        Crop.CORN.name -> R.string.corn
        Crop.COTTON.name -> R.string.cotton
        Crop.APPLE.name -> R.string.apple
        Crop.SUGARCANE.name -> R.string.sugarcane
        else -> 0
    }
}

fun getCrop(
    cropId: Int
): String {
    return when (cropId) {
        R.string.tomato -> Crop.TOMATO.name
        R.string.potato -> Crop.POTATO.name
        R.string.wheat -> Crop.WHEAT.name
        R.string.corn -> Crop.CORN.name
        R.string.cotton -> Crop.COTTON.name
        R.string.apple -> Crop.APPLE.name
        R.string.sugarcane -> Crop.SUGARCANE.name
        else -> ""
    }
}