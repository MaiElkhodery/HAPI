package com.example.hapi.presentation.home.common

import com.example.hapi.R
import com.example.hapi.util.Crop

fun getCropIcon(
    crop:Crop
): Int {
    return when(crop){
        Crop.APPLE -> R.drawable.apple
        Crop.TOMATO -> R.drawable.tomato
        Crop.POTATO -> R.drawable.potato
        Crop.WHEAT -> R.drawable.wheat
        Crop.CORN -> R.drawable.corn
        Crop.COTTON -> R.drawable.cotton
        Crop.SUGARCANE -> R.drawable.sugar_cane
    }
}