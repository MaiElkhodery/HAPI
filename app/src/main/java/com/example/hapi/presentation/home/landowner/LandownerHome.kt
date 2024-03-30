package com.example.hapi.presentation.home.landowner

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.hapi.R
import com.example.hapi.data.model.Detection
import com.example.hapi.data.model.Disease
import com.example.hapi.presentation.home.DetectionAndLandFeatureRow
import com.example.hapi.presentation.home.HomeHeader
import com.example.hapi.util.LandAction

@Composable
fun LandownerHome() {
    Column {

        HomeHeader(imageId = R.drawable.user_photo, username = "ahmed") {

        }

        DetectionAndLandFeatureRow(
            lastLandAction = com.example.hapi.data.model.LandAction(
                LandAction.FERTILIZATION.name,
                "2021-09-01",
                "12:00"
            ),
            lastDetection = Detection(
                username = "John Doe",
                date = "12/12/2021",
                time = "12:00",
                image_path = "",
                possible_diseases = emptyList(),
                crop = "",
            )
        )
    }
}

@Preview
@Composable
fun LandownerHomePreview() {
    LandownerHome()
}