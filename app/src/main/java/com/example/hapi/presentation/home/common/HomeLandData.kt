package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.domain.model.LandAction
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.PurpleGrey40
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.DarkGreenBlackText
import com.example.hapi.util.Dimens
import com.example.hapi.util.FeatureInfo

@Composable
fun HomeLandData(
    modifier: Modifier = Modifier,
    detectionUsername: String,
    detectionDate: String,
    detectionTime: String,
    byteArray: ByteArray? = null,
    imageUrl: String = "",
    lastLandAction: LandAction,
    lastFarmerUsername: String,
    lastFarmerDate: String,
    lastFarmerTime: String,
    onClickDetectionDetailsIcon: () -> Unit
) {
    var isDetectionSelected by remember {
        mutableStateOf(false)
    }
    var isLandSelected by remember {
        mutableStateOf(false)
    }
    var isFarmerSelected by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(70.dp),
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
                            isDetectionSelected = true
                            isLandSelected = false
                            isFarmerSelected = false
                        },
                    text = stringResource(id = R.string.detection),
                    isSelected = isDetectionSelected
                )
                FeatureBox(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            isLandSelected = true
                            isDetectionSelected = false
                            isFarmerSelected = false
                        },
                    text = stringResource(id = R.string.land),
                    isSelected = isLandSelected
                )
                FeatureBox(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            isLandSelected = false
                            isDetectionSelected = false
                            isFarmerSelected = true
                        },
                    text = stringResource(id = R.string.connected_farmer),
                    isSelected = isFarmerSelected
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                when {
                    isDetectionSelected -> {

                        if (detectionDate.isNotBlank()) {

                            LastDetectionContent(
                                modifier = Modifier.height(Dimens.home_box_height),
                                username = detectionUsername,
                                date = detectionDate,
                                time = detectionTime,
                                byteArray = byteArray,
                                imageUrl = imageUrl,
                            ) {
                                onClickDetectionDetailsIcon()
                            }
                        } else {
                            NotFoundWarning(
                                modifier = Modifier.height(Dimens.home_box_height),
                                warning = stringResource(id = R.string.no_detection),
                                warningDetails = stringResource(id = R.string.no_detection_details)
                            )
                        }
                    }

                    isLandSelected -> {
                        if (lastLandAction.name.isNotBlank()) {
                            LastLandActionContent(
                                modifier = Modifier.height(Dimens.home_box_height),
                                action = com.example.hapi.util.LandAction.valueOf(lastLandAction.name),
                                date = lastLandAction.date,
                                time = lastLandAction.time
                            )
                        } else {
                            NotFoundWarning(
                                modifier = Modifier.height(Dimens.home_box_height),
                                warning = stringResource(id = R.string.no_land),
                                warningDetails = stringResource(id = R.string.no_land_details)
                            )
                        }
                    }

                    isFarmerSelected -> {
                        if (lastFarmerDate.isNotBlank()) {
                            LastFarmerContent(
                                modifier = Modifier.height(Dimens.home_box_height),
                                date = lastFarmerDate,
                                time = lastFarmerTime,
                                farmerName = lastFarmerUsername
                            )
                        } else {
                            NotFoundWarning(
                                modifier = Modifier.height(Dimens.home_box_height),
                                warning = stringResource(id = R.string.no_farmer),
                                warningDetails = stringResource(id = R.string.no_farmer_details)
                            )

                        }
                    }
                }
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
                color = DarkGreenAppColor
            )
            .fillMaxWidth()
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            color = if (isSelected) YellowAppColor else GreenAppColor,
            fontSize = 12.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_extrabold
                )
            ),
            text = text,
            textAlign = TextAlign.Center,
            textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None
        )
    }
}

@Composable
fun LastFarmerContent(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    farmerName: String
) {
    Column(
        modifier = modifier
            .background(YellowAppColor)
            .padding(vertical = 12.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DarkGreenBlackText(
            modifier = Modifier.padding(bottom = 5.dp),
            size = 15,
            text = stringResource(id = R.string.last_connected_farmer)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.farmer),
                contentDescription = "farmer",
                modifier = Modifier.size(42.dp)
            )
            DarkGreenBlackText(
                size = 15, text = farmerName,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Row {
            ActionInfo(
                data = date,
                action = FeatureInfo.DATE,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            ActionInfo(
                data = time,
                action = FeatureInfo.TIME,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
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
        lastFarmerUsername = "John Doe",
        lastFarmerDate = "",
        lastFarmerTime = "12:00 PM",
        detectionUsername = "John Doe",
        detectionDate = "12/12/2021",
        detectionTime = "12:00 PM"
    ) {}
}