package com.example.hapi.presentation.main

import androidx.compose.foundation.background
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
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.DarkGreenBlackText

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
            DarkGreenBlackText(text = text, size = 16)
        }

        Box(
            modifier = Modifier
                .background(DarkGreenAppColor)
                .weight(1f)
                .fillMaxHeight()
                .padding(vertical = 5.dp)
                .height(IntrinsicSize.Min),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { onClick() }
            ) {
                Icon(
                    modifier = Modifier.size(120.dp),
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "next button",
                    tint = YellowAppColor
                )
            }
        }
    }
}

@Preview
@Composable
fun MainButtonPreview() {
    MainButton(text = "SIGN UP") {

    }
}