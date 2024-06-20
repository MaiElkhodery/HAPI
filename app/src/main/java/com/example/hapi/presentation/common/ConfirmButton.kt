package com.example.hapi.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.ScreenSize
import com.example.hapi.util.getScreenWidth

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    width: Dp,
    isEnglish: Boolean = true,
    text: String,
    onClick: () -> Unit
) {
    val fontSize = when (getScreenWidth(width)) {
        ScreenSize.SMALL -> 12
        ScreenSize.NORMAL -> 16
        ScreenSize.LARGE -> 18
        ScreenSize.XLARGE -> 20
    }
    val iconSize = when (getScreenWidth(width)) {
        ScreenSize.SMALL -> 18
        ScreenSize.NORMAL -> 22
        ScreenSize.LARGE -> 28
        ScreenSize.XLARGE -> 30
    }
    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp)),
        ) {

            if (!isEnglish) ContinueButton(isEnglish = false, iconSize = iconSize) { onClick() }
            GreenTextBox(
                fontSize = fontSize,
                text = text
            )
            if (isEnglish) ContinueButton(isEnglish = true, iconSize = iconSize) { onClick() }

        }
    }

}

@Composable
private fun GreenTextBox(
    fontSize: Int,
    text: String
) {
    Box(
        modifier = Modifier
            .background(YellowAppColor)
            .fillMaxHeight()
            .padding(horizontal = 26.dp),
        contentAlignment = Alignment.Center
    ) {


        GreenBlackText(size = fontSize, text = text)

    }

}

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    iconSize: Int,
    isEnglish: Boolean = true,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .background(DarkGreenAppColor)
            .padding(8.dp)
            .fillMaxHeight()
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        Icon(
            modifier = Modifier.size(iconSize.dp),
            painter = painterResource(
                id =
                if (isEnglish) R.drawable.continue_icon
                else R.drawable.continue_icon_ar
            ),
            contentDescription = "next button",
            tint = YellowAppColor
        )
    }

}

@Preview
@Composable
private fun ConfirmButtonPreview() {
    ConfirmButton(
        Modifier,
        width = 200.dp,
        text = "CONFIRM"
    ) {}
}