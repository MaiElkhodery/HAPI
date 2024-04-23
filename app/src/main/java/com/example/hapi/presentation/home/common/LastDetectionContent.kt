package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.BASE_URL
import com.example.hapi.util.DarkGreenBlackText
import com.example.hapi.util.FeatureInfo
import com.example.hapi.util.toBitmap

@Composable
fun LastDetectionContent(
    username: String,
    date: String,
    time: String,
    byteArray: ByteArray? = null,
    imageUrl: String = "",
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .background(YellowAppColor)
            .padding(vertical = 12.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DarkGreenBlackText(
            size = 15,
            text = stringResource(id = R.string.last_detection)
        )
        LastDetectionInfo(
            username = username,
            date = date,
            time = time,
            byteArray = byteArray,
            imageUrl = imageUrl
        ) {
            onClick()
        }
    }

}

@Composable
private fun LastDetectionInfo(
    modifier: Modifier = Modifier,
    username: String,
    date: String,
    time: String,
    byteArray: ByteArray? = null,
    imageUrl: String = "",
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = modifier
                .weight(.82f)
                .clip(RoundedCornerShape(5.dp))
                .padding(end = 12.dp)
        ) {
            if (byteArray != null && byteArray.isNotEmpty()) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp)),
                    bitmap = byteArray.toBitmap().asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            } else if (imageUrl.isNotBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(5.dp)),
                    model = BASE_URL + imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        DetectionInfo(
            modifier = Modifier.weight(1f),
            username = username,
            date = date,
            time = time
        )
        DetectionDetailsIcon(
            modifier = Modifier
                .weight(0.3f)
                .clip(RoundedCornerShape(6.dp)),
        ) {
            onClick()
        }
    }
}

@Composable
fun DetectionDetailsIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable {
                onClick()
            }
            .background(DarkGreenAppColor)
            .padding(horizontal = 4.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(27.dp),
            painter = painterResource(id = R.drawable.details),
            contentDescription = "Details",
            tint = YellowAppColor
        )
    }
}

@Composable
fun DetectionInfo(
    modifier: Modifier = Modifier,
    color: Color = DarkGreenAppColor,
    fontSize: Int = 12,
    username: String,
    date: String,
    time: String
) {
    Column(
        modifier = modifier
    ) {
        ActionInfo(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .align(Alignment.Start),
            horizontalArrangement = Arrangement.Start,
            color = color,
            fontSize = fontSize,
            data = username,
            action = FeatureInfo.USER
        )
        ActionInfo(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .align(Alignment.Start),
            horizontalArrangement = Arrangement.Start,
            color = color,
            fontSize = fontSize,
            data = date,
            action = FeatureInfo.DATE
        )
        ActionInfo(
            modifier = Modifier
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Start,
            color = color,
            fontSize = fontSize,
            data = time,
            action = FeatureInfo.TIME
        )
    }
}

@Preview
@Composable
private fun HomeOperationsDisplayPreview() {
    LastDetectionContent(
        username = "John Doe",
        date = "12/12/2021",
        time = "12:00",
    ) {

    }

}