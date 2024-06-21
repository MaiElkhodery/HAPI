package com.example.hapi.presentation.settings.support

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.common.GreenBlackText
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun HelpAndSupportOptions(
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp,
    onFQAClick: () -> Unit,
    onCallNowClick: () -> Unit
) {
    val fontSize = when {
        width <= 360.dp -> 12
        width in 360.dp..400.dp -> 14
        else -> 16
    }
    val imageSize = when {
        height <= 600.dp -> 110.dp
        height in 600.dp..855.dp -> 160.dp
        else -> 180.dp
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.help_and_support_icon),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(imageSize)
            )
        }

        helpAndSupportOption(
            text = stringResource(id = R.string.fqa_text),
            btnText = stringResource(id = R.string.fqa),
            fontSize = fontSize,
            imageId = R.drawable.fqa_icon
        ) {
            onFQAClick()
        }

        helpAndSupportOption(
            text = stringResource(id = R.string.call_now_text),
            btnText = stringResource(id = R.string.call_now),
            fontSize = fontSize,
            imageId = R.drawable.call_icon
        ) {
            onCallNowClick()
        }
    }
}

@Composable
fun helpAndSupportOption(
    modifier: Modifier = Modifier,
    text: String,
    btnText: String,
    fontSize: Int,
    imageId: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        YellowBlackText(
            size = fontSize - 1,
            text = text,
            modifier = Modifier.weight(1.3f),
            align = TextAlign.Start
        )

        ButtonWithEndIcon(
            text = btnText,
            imageId = imageId,
            fontSize = fontSize,
            modifier = Modifier
                .weight(1.1f)
                .padding(start = 8.dp)
        ) {
            onClick()
        }
    }
}

@Composable
fun ButtonWithEndIcon(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 14,
    imageId: Int,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {

        Box(
            modifier = Modifier
                .background(YellowAppColor)
                .fillMaxHeight()
                .weight(1f)
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            GreenBlackText(size = fontSize, text = text)
        }

        Box(
            modifier = Modifier
                .weight(.4f)
                .background(DarkGreenAppColor)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(5.dp)
                    .size(25.dp),
                painter = painterResource(id = imageId),
                contentDescription = null,
                tint = YellowAppColor
            )
        }
    }

}


@Preview
@Composable
private fun HelpAndSupportContentPreview() {
    HelpAndSupportOptions(
        width = 400.dp,
        height = 800.dp,
        onFQAClick = {},
        onCallNowClick = {}
    )
}