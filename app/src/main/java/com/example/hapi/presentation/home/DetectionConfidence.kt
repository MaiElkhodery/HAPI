package com.example.hapi.presentation.home

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.text.GreenBlackText
import com.example.hapi.util.text.GreenBoldText

@Composable
fun DetectionLowConfidence(
    name: String,
    confidence: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(5.dp))
    ) {
        DiseaseDetectionText(
            name = name, confidence = confidence,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.2f)
                .background(DarkGreenAppColor)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.more_icon),
                contentDescription = null,
                tint = YellowAppColor,
                modifier = Modifier
                    .padding(9.dp)
                    .size(37.dp)
            )
        }
    }

}

@Composable
private fun DiseaseDetectionText(
    name: String,
    confidence: String,
    alignment: Alignment.Horizontal = Alignment.Start,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(YellowAppColor)
            .padding(horizontal = 12.dp, vertical = 18.dp)
            .fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        GreenBlackText(size = 20, text = name)
        GreenBoldText(size = 20, text = confidence)
    }
}

@Preview
@Composable
private fun DetectionLowConfidenceCardPreview() {
    DetectionLowConfidence(
        name = "EARLY BLIGHT",
        confidence = "50% CONFIDENCE"
    )
}