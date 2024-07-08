package com.example.hapi.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.hapi.presentation.common.DarkGreenBlackText
import com.example.hapi.presentation.common.YellowMediumText
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun SignOption(
    modifier: Modifier = Modifier,
    fontSize: Int,
    iconSize: Dp,
    isEnglish: Boolean,
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowMediumText(
            text = text,
            modifier = Modifier.padding(bottom = 12.dp),
            size = fontSize
        )
        SignButton(
            text = buttonText,
            isEnglish = isEnglish,
            fontSize = fontSize,
            iconSize = iconSize,
            onClick = onClick
        )
    }
}

@Composable
fun SignButton(
    text: String,
    fontSize: Int,
    iconSize: Dp,
    isEnglish: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .height(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier
                .weight(4f)
                .fillMaxHeight()
                .background(YellowAppColor),
            contentAlignment = Alignment.Center
        ) {
            DarkGreenBlackText(text = text, size = fontSize)
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(DarkGreenAppColor)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    id = if (isEnglish) R.drawable.continue_icon
                    else R.drawable.continue_icon_ar
                ),
                contentDescription = "next button",
                tint = YellowAppColor,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .size(iconSize)
            )
        }
    }
}

@Composable
fun SetUpBottomImage(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.main_crop),
            contentDescription = null
        )
    }
}
@Preview
@Composable
fun SignButtonPreview() {
    SignButton(text = "SIGN UP", isEnglish = true, fontSize = 15, iconSize = 32.dp) {

    }
}