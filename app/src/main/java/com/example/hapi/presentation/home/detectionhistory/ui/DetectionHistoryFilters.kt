package com.example.hapi.presentation.home.detectionhistory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.GreenBlackText

@Composable
fun DetectionHistoryFilters(
    modifier: Modifier = Modifier,
    onAllDetectionsSelected: () -> Unit,
    onYourDetectionsSelected: () -> Unit
) {
    var isYourDetectionsSelected by remember { mutableStateOf(true) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 5.dp)
                .background(
                    if (isYourDetectionsSelected) YellowAppColor
                    else DarkGreenAppColor
                )
                .weight(1f)
                .clickable {
                    isYourDetectionsSelected = true
                    onYourDetectionsSelected()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .padding(horizontal = 5.dp, vertical = 5.dp),
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                tint =  GreenAppColor
            )
            GreenBlackText(
                size = 10,
                text = stringResource(id = R.string.your_detections)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 5.dp)
                .background(
                    if (!isYourDetectionsSelected) YellowAppColor
                    else DarkGreenAppColor
                )
                .clickable {
                    isYourDetectionsSelected = false
                    onAllDetectionsSelected()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 5.dp),
                painter = painterResource(id = R.drawable.farmers),
                contentDescription = null,
                tint =  GreenAppColor
            )
            GreenBlackText(
                size = 10,
                text = stringResource(id = R.string.all_detections)
            )
        }
    }
}

@Preview
@Composable
private fun DetectionHistoryFiltersPreview() {
    DetectionHistoryFilters(
        onAllDetectionsSelected = {},
        onYourDetectionsSelected = {}
    )
}