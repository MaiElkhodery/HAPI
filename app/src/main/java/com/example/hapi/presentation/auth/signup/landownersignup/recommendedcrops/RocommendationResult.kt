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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.common.ContinueButton
import com.example.hapi.presentation.common.GreenBlackText
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.presentation.home.common.getCropName
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.Crop

@Composable
fun RecommendationResult(
    modifier: Modifier = Modifier,
    width: Dp,
    isEnglish: Boolean,
    topCrops: List<Crop>,
    onClick: (Int) -> Unit
) {

    val smallFontSize = when {
        width < 360.dp -> 11
        width in 360.dp..400.dp -> 13
        else -> 15
    }
    val mediumFontSize = when {
        width < 360.dp -> 12
        width in 360.dp..400.dp -> 15
        else -> 17
    }
    val largeFontSize = when {
        width < 360.dp -> 18
        width in 360.dp..400.dp -> 20
        else -> 22
    }

    val iconSize = when {
        width < 360.dp -> 18
        width in 360.dp..400.dp -> 22
        else -> 24
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowBlackText(
            size = mediumFontSize,
            text = stringResource(id = R.string.choose_recommedation),
            modifier = Modifier.padding(bottom = 22.dp)
        )
        YellowBlackText(
            size = smallFontSize,
            text = stringResource(id = R.string.top_recommendation),
            modifier = Modifier.padding(bottom = 9.dp)
        )

        val cropNamesId: MutableList<Int> = mutableListOf()
        topCrops.forEach { crop ->
            cropNamesId.add(getCropName(crop = crop.name))
        }

        RecommendedCrop(
            modifier = Modifier.padding(horizontal = 30.dp),
            fontSize = largeFontSize,
            isEnglish = isEnglish,
            crop = cropNamesId[0],
            iconSize = iconSize
        ) {
            onClick(cropNamesId[0])
        }
        YellowBlackText(
            size = 13,
            text = stringResource(id = R.string.other_options),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        RecommendedCrop(
            modifier = Modifier.padding(horizontal = 58.dp),
            fontSize=mediumFontSize,
            isEnglish = isEnglish,
            crop = cropNamesId[1],
            iconSize = iconSize
        ) {
            onClick(cropNamesId[1])
        }
        RecommendedCrop(
            modifier = Modifier.padding(horizontal = 58.dp, vertical = 10.dp),
            fontSize=mediumFontSize,
            isEnglish = isEnglish,
            crop = cropNamesId[2],
            iconSize = iconSize
        ) {
            onClick(cropNamesId[2])
        }
    }
}

@Composable
fun RecommendedCrop(
    modifier: Modifier = Modifier,
    fontSize:Int,
    isEnglish: Boolean,
    iconSize: Int,
    crop: Int,
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

            if(!isEnglish) ContinueButton(
                modifier = Modifier.fillMaxWidth().weight(.8f),
                iconSize=iconSize
            ) { onClick() }
            CropRow(cropId = crop, textSize = fontSize, modifier = Modifier.weight(3f))
           if(isEnglish) ContinueButton(
                modifier = Modifier.fillMaxWidth().weight(.8f),
                iconSize=iconSize
            ) { onClick() }

        }
    }

}

@Composable
fun CropRow(
    cropId: Int,
    textSize: Int,
    modifier: Modifier
) {

    val cropName = stringResource(id = cropId)
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
            painter = painterResource(id = getCropIcon(cropName)),
            contentDescription = "crop image"
        )

        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            GreenBlackText(size = textSize, text = cropName)

        }

    }
}

@Preview
@Composable
private fun CropRecommendationContentPreview() {
    RecommendationResult(
        isEnglish = true,
        width = 300.dp,
        topCrops = listOf(
            Crop.APPLE,
            Crop.POTATO,
            Crop.CORN
        )
    ) {

    }
}