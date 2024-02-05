package com.example.hapi.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
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
fun MainButton(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(5.dp))
    ) {

        Box(
            modifier = Modifier
                .background(YellowAppColor)
                .weight(4f)
                .fillMaxHeight()
                .height(IntrinsicSize.Min),
            contentAlignment = Alignment.Center
        ) {
            BlackGreenText(text = text)
        }

        Box(
            modifier = Modifier
                .background(DarkGreenAppColor)
                .weight(1f)
                .fillMaxHeight()
                .padding(vertical = 11.dp)
                .height(IntrinsicSize.Min),
            contentAlignment = Alignment.Center
        ){
            IconButton(
                onClick = { onClick() }
            ) {
                Icon(
                    modifier = Modifier.size(120.dp),
                    imageVector =  Icons.Default.PlayArrow,
                    contentDescription = "next button",
                    tint = YellowAppColor
                )
            }
        }
    }
}
@Composable
fun BlackGreenText(
    text: String
) {
    Text(
        modifier = Modifier,
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

@Preview
@Composable
fun MainButtonPreview() {
    MainButton(text = "SIGN UP") {
        
    }
}