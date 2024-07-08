package com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.common.GreenBlackText
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.common.YellowBoldText
import com.example.hapi.ui.theme.YellowAppColor


@Composable
fun CropStrategyOptions(
    modifier: Modifier = Modifier,
    width: Dp,
    onClickRecommendation: () -> Unit,
    onClickHaveCrop: () -> Unit
) {

    val largeFontSize = when {
        width <= 360.dp -> 12
        width in 360.dp..400.dp -> 15
        else -> 17
    }
    val smallFontSize = when {
        width <= 360.dp -> 11
        width in 360.dp..400.dp -> 13
        else -> 15
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowBlackText(
            size = largeFontSize,
            text = stringResource(id = R.string.do_you),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        StrategyOption(
            smallFontSize = smallFontSize,
            largeFontSize = largeFontSize,
            cardText = stringResource(id = R.string.recommend_crop),
            description = stringResource(id = R.string.use_recommendation),
            onClick = onClickRecommendation
        )
        StrategyOption(
            smallFontSize = smallFontSize,
            largeFontSize = largeFontSize,
            cardText = stringResource(id = R.string.have_crop),
            description = stringResource(id = R.string.choose_crop),
            onClick = onClickHaveCrop
        )
    }
}

@Composable
private fun StrategyOption(
    smallFontSize: Int,
    largeFontSize: Int,
    cardText: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OptionCard(
            modifier = Modifier.weight(1f),
            fontSize = largeFontSize,
            text = cardText
        ) {
            onClick()
        }
        YellowBoldText(
            text = description,
            size = smallFontSize,
            modifier = Modifier.weight(1.1f)
        )

    }
}


@Composable
private fun OptionCard(
    modifier: Modifier,
    fontSize: Int,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(end = 11.dp)
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(5.dp))
            .background(YellowAppColor)
            .clickable {
                onClick()
            }
    ) {
        GreenBlackText(
            size = fontSize,
            text = text,
            modifier = Modifier
                .padding(vertical = 26.dp, horizontal = 2.dp)
                .fillMaxSize()
        )
    }
}


@Preview
@Composable
private fun ContentPreview() {
    CropStrategyOptions(Modifier,
        width = 300.dp,
        {}) {}
}