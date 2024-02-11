package com.example.hapi.data.model

import com.example.hapi.R

data class Crop(
    val name: String,
    val image: Int
)

val crops = mutableListOf(
    Crop(
        "TOMATO",
        R.drawable.tomato
    ),
    Crop(
        "POTATO",
        R.drawable.potato
    ),
    Crop(
        "ORANGE",
        R.drawable.orange
    ),
    Crop(
        "GRAPE",
        R.drawable.grape
    ),
    Crop(
        "STRAWBERRY",
        R.drawable.strawberry
    ),
    Crop(
        "WHEAT",
        R.drawable.wheat
    ),
    Crop(
        "CORN",
        R.drawable.corn
    )
)