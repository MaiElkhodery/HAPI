package com.example.hapi.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.text.GreenBlackText

@Composable
fun SettingsOptionRow(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(5.dp))
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(YellowAppColor)
                .padding(horizontal = 11.dp)
                .weight(1f),
            contentAlignment = Alignment.CenterStart
        ){
            GreenBlackText(size = 16, text = text)
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.11f)
                .background(DarkGreenAppColor)
                .padding(8.dp)
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = null,
                tint = YellowAppColor,
                modifier = Modifier
                    .size(26.dp)
            )
        }
    }
}

@Preview
@Composable
fun SettingOptionRowPreview() {
    SettingsOptionRow(
        text = "setting"
    ){}
}