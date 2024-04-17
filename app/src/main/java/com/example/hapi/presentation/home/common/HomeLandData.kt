package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.domain.model.LandAction
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.PurpleGrey40
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun HomeLandData(
    modifier: Modifier = Modifier,
    username: String,
    date: String,
    time: String,
    image_url: String,
    byteArray: ByteArray? = null,
    lastLandAction: LandAction,
    onClickDetectionDetailsIcon: () -> Unit
) {
    var detectionIsSelected by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(100.dp),
            painter = painterResource(id = R.drawable.crop_profile),
            contentDescription = "home crop image"
        )
        Column {

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
                    username = username,
                    date = date,
                    time = time,
                    image_url = image_url,
                    byteArray = byteArray
                ) {
                    onClickDetectionDetailsIcon()
                }
            } else {
                LastLandActionContent(
                    action = com.example.hapi.util.LandAction.valueOf(lastLandAction.name),
                    date = lastLandAction.date,
                    time = lastLandAction.time
                )
            }
        }

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
            .background(
                color = if (isSelected) YellowAppColor else DarkGreenAppColor
            )
            .fillMaxWidth()
            .padding(3.dp),
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
    HomeLandData(
        lastLandAction = LandAction(
            name = com.example.hapi.util.LandAction.FERTILIZATION.name,
            date = "12/12/2021",
            time = "12:00 PM"
        ),
        username = "John Doe",
        date = "12/12/2021",
        time = "12:00 PM",
        image_url = "",
    ) {}
}