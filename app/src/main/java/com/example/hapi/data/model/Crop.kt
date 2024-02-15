package com.example.hapi.data.model

import com.example.hapi.R

data class Crop(
    val type: CropType,
    val imageId: Int
)

val crops = mutableListOf(
    Crop(
        CropType.TOMATO,
        R.drawable.tomato
    ),
    Crop(
        CropType.POTATO,
        R.drawable.potato
    ),
    Crop(
        CropType.ORANGE,
        R.drawable.orange
    ),
    Crop(
        CropType.GRAPE,
        R.drawable.grape
    ),
    Crop(
        CropType.STRAWBERRY,
        R.drawable.strawberry
    ),
    Crop(
        CropType.WHEAT,
        R.drawable.wheat
    ),
    Crop(
        CropType.CORN,
        R.drawable.corn
    )
)

enum class CropType {
    TOMATO, POTATO, GRAPE, WHEAT, CORN, STRAWBERRY, ORANGE
}