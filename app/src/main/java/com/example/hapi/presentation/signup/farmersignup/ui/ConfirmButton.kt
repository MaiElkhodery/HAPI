package com.example.hapi.presentation.signup.farmersignup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun ConfirmButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box (
        modifier = modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier =Modifier
                .clip(RoundedCornerShape(5.dp)),
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
            .padding(horizontal = 24.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = GreenAppColor,
            fontSize = 18.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_black
                )
            )
        )
    }

}

@Composable
private fun ContinueButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(DarkGreenAppColor)
            .fillMaxHeight()
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(46.dp),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "next button",
            tint = YellowAppColor
        )
    }
}

@Preview
@Composable
private fun ConfirmButtonPreview() {
    ConfirmButton(Modifier, "CONFIRM&SIGNUP") {}
}