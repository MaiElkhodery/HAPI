package com.example.hapi.presentation.home.landhistory.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.GreenBlackText

@Composable
fun LandHistoryFilters(
    modifier: Modifier = Modifier,
    onFilterSelected: (LandFilterType) -> Unit
) {
    var selectedFilter by remember { mutableStateOf(LandFilterType.All) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FilterBox(
            isSelected = selectedFilter == LandFilterType.All,
            text = stringResource(id = R.string.all_actions),
            onClick = {
                selectedFilter = LandFilterType.All
                onFilterSelected(LandFilterType.All)
            }
        )

        FilterIconBox(
            isSelected = selectedFilter == LandFilterType.Fertilization,
            selectedIconResId = R.drawable.fertilization,
            unselectedIconResId = R.drawable.unselected_fertilization,
            onClick = {
                selectedFilter = LandFilterType.Fertilization
                onFilterSelected(LandFilterType.Fertilization)
            }
        )

        FilterIconBox(
            isSelected = selectedFilter == LandFilterType.Irrigation,
            selectedIconResId = R.drawable.irrigation,
            unselectedIconResId = R.drawable.unselected_irrigation,
            onClick = {
                selectedFilter = LandFilterType.Irrigation
                onFilterSelected(LandFilterType.Irrigation)
            }
        )
    }
}
@Composable
fun FilterBox(
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) YellowAppColor else DarkGreenAppColor

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(3.dp))
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 9.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        GreenBlackText(
            size = 10,
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .height(IntrinsicSize.Max)
        )
    }
}

@Composable
fun FilterIconBox(
    isSelected: Boolean,
    @DrawableRes selectedIconResId: Int,
    @DrawableRes unselectedIconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) YellowAppColor else DarkGreenAppColor

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(3.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = if (isSelected) selectedIconResId else unselectedIconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

enum class LandFilterType {
    All, Fertilization, Irrigation
}

@Preview
@Composable
private fun LandHistoryFiltersPreview() {
    LandHistoryFilters{}
}