package com.example.hapi.presentation.signup.farmersignup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun ConfirmButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(5.dp))
    ) {

        BlackGreenText(
            text = text,
            modifier = Modifier.weight(5f)
        )

        ContinueButton(modifier = Modifier.weight(1f)) {
            onClick()
        }

    }
}

@Composable
private fun BlackGreenText(
    text: String,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(YellowAppColor)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = DarkGreenAppColor,
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
    modifier: Modifier,
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