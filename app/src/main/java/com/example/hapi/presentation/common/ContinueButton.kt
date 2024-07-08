package com.example.hapi.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun NextButton(
    iconSize: Dp,
    onlClickContinue: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(YellowAppColor)
            .clickable {
                onlClickContinue()
            }
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .size(iconSize),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = null,
            tint = GreenAppColor
        )
    }
}