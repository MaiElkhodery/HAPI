package com.example.hapi.presentation.home.detectionhistory.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            .height(IntrinsicSize.Max)
    ) {
        FilterOption(
            modifier = Modifier.weight(1f),
            isSelected = isYourDetectionsSelected,
            iconResId = R.drawable.user,
            textResId = R.string.your_detections,
            onClick = {
                isYourDetectionsSelected = true
                onYourDetectionsSelected()
            }
        )
        Spacer(modifier = Modifier.width(10.dp))
        FilterOption(
            modifier = Modifier.weight(1f),
            isSelected = !isYourDetectionsSelected,
            iconResId = R.drawable.farmers,
            textResId = R.string.all_detections,
            onClick = {
                isYourDetectionsSelected = false
                onAllDetectionsSelected()
            }
        )
    }
}

@Composable
fun FilterOption(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    iconResId: Int,
    textResId: Int,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) YellowAppColor else DarkGreenAppColor

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .fillMaxHeight()
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = GreenAppColor,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        GreenBlackText(
            size = 10,
            text = stringResource(id = textResId)
        )
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