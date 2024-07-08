package com.example.hapi.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ScreenSize { SMALL, NORMAL, LARGE, XLARGE }


fun getScreenWidth(screenWidth: Dp): ScreenSize {
    return when {
        screenWidth <= 360.dp -> ScreenSize.SMALL
        screenWidth in 360.dp..400.dp -> ScreenSize.NORMAL
        screenWidth > 400.dp -> ScreenSize.LARGE
        else -> ScreenSize.XLARGE
    }
}