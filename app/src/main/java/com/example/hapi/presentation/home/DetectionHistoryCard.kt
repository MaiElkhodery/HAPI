package com.example.hapi.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun DetectionHistoryCard(
    modifier: Modifier = Modifier,
    username: String,
    date: String,
    time: String,
    imageId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(1.dp))

    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(YellowAppColor)
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            DiseaseImage(
                imageId = imageId,
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
    imageId: Int
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = contentScale
        )
    }
}

@Preview
@Composable
private fun DetectionHistoryCardPreview() {
    DetectionHistoryCard(
        username = "John Doe",
        date = "12/12/2021",
        time = "12:00",
        imageId = R.drawable.disease_sample
    ) { }
}