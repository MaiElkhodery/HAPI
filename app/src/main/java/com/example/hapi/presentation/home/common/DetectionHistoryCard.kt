package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.BASE_URL
import com.example.hapi.util.toBitmap

@Composable
fun DetectionHistoryCard(
    modifier: Modifier = Modifier,
    username: String,
    date: String,
    time: String,
    imageUrl: String,
    byteArray: ByteArray? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .clickable {
                onClick()
            }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(YellowAppColor)
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DiseaseImage(
                imagePath = imageUrl,
                byteArrayImage = byteArray,
                modifier = Modifier
                    .weight(1f),
                contentScale = ContentScale.FillBounds
            )

            DetectionInfo(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                username = username,
                date = date,
                time = time
            )

            DetectionDetailsIcon(
                modifier = Modifier
                    .weight(.31f)
                    .fillMaxHeight()
            ) {
                onClick()
            }

        }
    }
}

@Composable
fun DiseaseImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    imagePath: String,
    byteArrayImage: ByteArray?
) {
    Box(
        modifier = modifier
    ) {
        if (byteArrayImage != null && byteArrayImage.isNotEmpty()) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                bitmap = byteArrayImage.toBitmap().asImageBitmap(),
                contentDescription = null,
                contentScale = contentScale
            )
        } else if (imagePath.isNotBlank()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = BASE_URL + imagePath,

                contentDescription = null,
                contentScale = contentScale
            )
        }
    }
}

@Preview
@Composable
private fun DetectionHistoryCardPreview() {
    DetectionHistoryCard(
        username = "John Doe",
        date = "12/12/2021",
        time = "12:00",
        imageUrl = "",
    ) {

    }
}