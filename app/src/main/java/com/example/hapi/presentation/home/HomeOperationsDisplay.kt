package com.example.hapi.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.data.model.Detection
import com.example.hapi.data.model.LandAction
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.PurpleGrey40
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun DetectionAndLandFeatureRow(
    modifier: Modifier = Modifier,
    lastLandAction: LandAction,
    lastDetection: Detection,
) {
    var detectionIsSelected by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 29.dp,
                    clip = false,
                    shape = RoundedCornerShape(10.dp),
                    spotColor = PurpleGrey40
                )
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

        if (detectionIsSelected) {
            LastDetectionContent(
                username = "John Doe",
                date = "12/12/2021",
                time = "12:00",
                imageId = R.drawable.disease_sample
            ) {}
        } else {
            LastLandActionContent(
                action = com.example.hapi.util.LandAction.FERTILIZATION,
                date = "12/12/2021",
                time = "12:00"
            )
        }

    }
}

@Composable
private fun FeatureBox(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean
) {

    Row(
        modifier = modifier
            .background(
                color = if (isSelected) YellowAppColor else DarkGreenAppColor
            )
            .fillMaxWidth()
            .padding(3.dp),
        horizontalArrangement = Arrangement.Center
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
    DetectionAndLandFeatureRow(
        lastLandAction = LandAction(
            name = com.example.hapi.util.LandAction.FERTILIZATION.name,
            date = "12/12/2021",
            time = "12:00 PM"
        ),
        lastDetection = Detection(
            id = 1,
            username = "John Doe",
            crop = "Wheat",
            image_path = "path",
            date = "12/12/2021",
            time = "12:00 PM",
            status = "status",
            possible_diseases = emptyList()
        )
    )
}