package com.example.hapi.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun DetectionOrLandRow() {
    Row {

    }
}

@Composable
private fun FeatureRow(

) {
    var detectionIsSelected by remember {
        mutableStateOf(true)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        FeatureBox(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    detectionIsSelected = true
                },
            text = stringResource(id = R.string.detection),
            isSelected = detectionIsSelected
        )
        FeatureBox(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    detectionIsSelected = false
                },
            text = stringResource(id = R.string.land),
            isSelected = !detectionIsSelected
        )
    }
}

@Composable
private fun FeatureBox(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isSelected) YellowAppColor else DarkGreenAppColor
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            color = if (isSelected) DarkGreenAppColor else YellowAppColor,
            fontSize = 12.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_extrabold
                )
            ),
            text = text,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
private fun HomeOperationsDisplayPreview() {

}