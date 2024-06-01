package com.example.hapi.presentation.auth.signup.landownersignup.recommendedcrops

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.common.ContinueButton
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.Crop
import com.example.hapi.util.GreenBlackText
import com.example.hapi.util.YellowBlackText

@Composable
fun RecommendedCropsList(
    modifier: Modifier = Modifier,
    topCrops: List<Crop>,
    onClick: (Crop) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowBlackText(
            size = 13,
            text = stringResource(id = R.string.top_recommendation),
            modifier = Modifier.padding(vertical = 9.dp)
        )

        RecommendedCrop(
            modifier = Modifier.padding(horizontal = 30.dp),
            crop = topCrops[0]
        ) {
            onClick(topCrops[0])
        }
        YellowBlackText(
            size = 13,
            text = stringResource(id = R.string.other_options),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        RecommendedCrop(
            modifier = Modifier.padding(horizontal = 58.dp),
            textSize = 15,
            crop = topCrops[1]
        ) {
            onClick(topCrops[1])
        }
        RecommendedCrop(
            modifier = Modifier.padding(horizontal = 58.dp, vertical = 10.dp),
            textSize = 15,
            crop = topCrops[2]
        ) {
            onClick(topCrops[2])
        }
    }
}

@Composable
fun RecommendedCrop(
    modifier: Modifier = Modifier,
    textSize: Int = 20,
    crop: Crop,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp)),
        ) {

            CropRow(crop = crop, textSize = textSize, modifier = Modifier.weight(3f))
            ContinueButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.8f)
            ) { onClick() }

        }
    }

}

@Composable
fun CropRow(
    crop: Crop,
    textSize: Int,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(YellowAppColor)
            .height(IntrinsicSize.Min)
            .padding(vertical = 11.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(
                    if (textSize == 20) 48.dp else 33.dp
                )
                .padding(end = 3.dp),
            painter = painterResource(id = getCropIcon(crop.name)),
            contentDescription = "crop image"
        )

        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            GreenBlackText(size = textSize, text = crop.name)

        }

    }
}

@Preview
@Composable
private fun CropRecommendationContentPreview() {
    RecommendedCropsList(
        topCrops = listOf(
            Crop.APPLE,
            Crop.POTATO,
            Crop.CORN
        )
    ) {

    }
}