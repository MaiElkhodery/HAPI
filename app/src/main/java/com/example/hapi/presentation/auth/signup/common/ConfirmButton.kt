package com.example.hapi.presentation.auth.signup.common

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    text: String,
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
                .clip(RoundedCornerShape(6.dp)),
        ) {

            GreenText(text = text)

            ContinueButton { onClick() }

        }
    }

}

@Composable
private fun GreenText(
    text: String
) {
    Box(
        modifier = Modifier
            .background(YellowAppColor)
            .fillMaxHeight()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {

        GreenBlackText(size = 16, text = text)

    }

}

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(DarkGreenAppColor)
            .fillMaxHeight()
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(42.dp),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "next button",
            tint = YellowAppColor
        )
    }
}

@Preview
@Composable
private fun ConfirmButtonPreview() {
    ConfirmButton(Modifier, "SIGNUP") {}
}