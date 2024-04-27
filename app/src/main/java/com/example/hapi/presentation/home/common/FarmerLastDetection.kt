package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hapi.R

@Composable
fun FarmerLastDetection(
    modifier: Modifier = Modifier,
    username: String,
    date: String,
    time: String,
    byteArray: ByteArray? = null,
    imageUrl: String = "",
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(70.dp),
            painter = painterResource(id = R.drawable.crop_profile),
            contentDescription = "home crop image"
        )
        LastDetectionContent(username = username, date = date, time = time, imageUrl = imageUrl) {
            onClick()
        }
    }
}