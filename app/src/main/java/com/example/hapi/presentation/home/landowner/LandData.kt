package com.example.hapi.presentation.home.landowner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.ui.theme.HomeHeaderBackgroundColor
import com.example.hapi.util.Crop
import com.example.hapi.util.YellowBlackText
import com.example.hapi.util.YellowBoldText

@Composable
fun LandData(
    modifier: Modifier = Modifier,
    crop: String,
    waterLevel: Int,
    npk: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(HomeHeaderBackgroundColor)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LandDataItem(data = crop, text = stringResource(id = R.string.crop), isCrop = true)
        LandDataItem(
            data = "$waterLevel%",
            text = stringResource(id = R.string.water_level),
            image = R.drawable.water_level
        )
        LandDataItem(
            data = npk,
            text = stringResource(id = R.string.npk_ratio),
            image = R.drawable.npk
        )
    }
}

@Composable
fun LandDataItem(
    image: Int = 0,
    data: String,
    text: String,
    isCrop: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (data.isNotBlank()) {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp),
                painter = painterResource(
                    id = if (isCrop) getCropIcon(
                        Crop.valueOf(data.uppercase())
                    ) else image
                ),
                contentDescription = "crop icon"
            )
        }

        Column {
            YellowBoldText(text = text, size = 11)
            YellowBlackText(size = 11, text = data)
        }

    }
}

@Preview
@Composable
private fun LandDataPreview() {
    LandData(
        crop = "WHEAT",
        waterLevel = 20,
        npk = "2-3-1"
    )
}