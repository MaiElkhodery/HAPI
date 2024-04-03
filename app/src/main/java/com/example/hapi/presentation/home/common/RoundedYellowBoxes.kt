package com.example.hapi.presentation.home.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun RoundedYellowBoxes(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(33.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 0..2) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        color = YellowAppColor,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .height(26.dp)
            )
        }
    }
}

@Preview
@Composable
fun RoundedYellowBoxesPreview() {
    RoundedYellowBoxes()
}