package com.example.hapi.presentation.home.detectiondetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.home.common.DetectionInfo
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.presentation.home.common.getCropName
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.BASE_URL

@Composable
fun DetectionDetailsData(
    modifier: Modifier = Modifier,
    crop: String,
    imageUrl: String,
    imageLocalUri: String,
    username: String,
    date: String,
    time: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {

        CropData(crop = crop)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (username.isBlank()) Box(modifier = Modifier
                .fillMaxWidth()
                .weight(.4f))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(width = 3.dp, color = YellowAppColor)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .weight(1.2f)

            ) {

                if (imageUrl.isNotBlank()) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(180.dp),
                        model = BASE_URL + imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                } else {

                    val painter = rememberAsyncImagePainter(imageLocalUri)
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(200.dp),
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }

            }
            if (username.isBlank()) Box(modifier = Modifier.fillMaxWidth().weight(.4f))
            if (username.isNotBlank())
                DetectionInfo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                        .weight(1f),
                    username = username,
                    date = date,
                    time = time,
                    color = YellowAppColor,
                    fontSize = 15
                )
        }
    }
}

@Composable
private fun CropData(
    crop: String
) {
    if (crop.isBlank()) return
    val cropName = stringResource(id = getCropName(crop = crop))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(31.dp),
            painter = painterResource(
                id = getCropIcon(
                    cropName
                )
            ),
            contentDescription = "crop icon"
        )
        YellowBlackText(size = 24, text = cropName)
    }
}