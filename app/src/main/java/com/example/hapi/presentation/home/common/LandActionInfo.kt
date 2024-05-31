package com.example.hapi.presentation.home.common

import androidx.compose.runtime.Composable
import com.example.hapi.R
import com.example.hapi.util.LandAction

@Composable
fun LandActionName(
    name: String
):Int {
    return when (name) {
        LandAction.FERTILIZATION.name -> R.string.fertilization
        LandAction.IRRIGATION.name -> R.string.irrigation
        else -> 0
    }
}