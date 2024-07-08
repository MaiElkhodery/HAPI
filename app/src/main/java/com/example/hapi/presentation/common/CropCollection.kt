package com.example.hapi.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.presentation.home.common.getCropName
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.Crop

@Composable
fun CropCollection(
    modifier: Modifier = Modifier,
    width: Dp,
    onClick: (String) -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Crop.entries.take(4).forEach { crop ->
                CropItem(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    width = width,
                    crop = crop.name,
                    onClick = { onClick(crop.name) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Crop.entries.drop(4).take(3).forEach { crop ->
                CropItem(
                    modifier = Modifier,
                    width = width,
                    crop = crop.name,
                    onClick = { onClick(crop.name) }
                )
            }
        }
    }
}

@Composable
private fun CropItem(
    modifier: Modifier = Modifier,
    crop: String,
    width: Dp,
    onClick: () -> Unit
) {
    val fontSize = when {
        width <= 360.dp -> 9
        width in 360.dp..400.dp -> 12
        else -> 14
    }
    val imageSize = when {
        width <= 360.dp -> 40.dp
        width in 360.dp..400.dp -> 44.dp
        else -> 48.dp
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val cropName = stringResource(id = getCropName(crop = crop))
        CropImageCard(
            imageId = getCropIcon(cropName),
            imageSize = imageSize
        ) {
            onClick()
        }
        YellowBoldText(
            text = stringResource(id = getCropName(crop = crop)),
            size = fontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Composable
private fun CropImageCard(
    imageId: Int,
    imageSize: Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .background(YellowAppColor)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .padding(10.dp)
                .size(imageSize),
            painter = painterResource(id = imageId),
            contentDescription = "crop image"
        )

    }
}

@Preview
@Composable
private fun CropChooseContentPreview() {
    CropCollection(
        width = 100.dp
    ) {}
}




