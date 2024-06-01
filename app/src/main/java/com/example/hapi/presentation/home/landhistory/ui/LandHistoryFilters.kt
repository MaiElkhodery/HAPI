package com.example.hapi.presentation.home.landhistory.ui

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
    onAllActionsSelected: () -> Unit,
    onFertilizationSelected: () -> Unit,
    onIrrigationSelected: () -> Unit
) {
    var isAllActionsSelected by remember { mutableStateOf(true) }
    var isFertilizationSelected by remember { mutableStateOf(false) }
    var isIrrigationSelected by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isAllActionsSelected) YellowAppColor else DarkGreenAppColor
                )
                .padding(10.dp)
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    isFertilizationSelected = false
                    isIrrigationSelected = false
                    isAllActionsSelected = true
                    onAllActionsSelected()
                },
            contentAlignment = Alignment.Center
        ) {
            GreenBlackText(
                size = 10,
                text = stringResource(id = R.string.all_actions),
                modifier = Modifier.align(Alignment.Center).height(IntrinsicSize.Max)
            )
        }

        Box(
            modifier = Modifier
                .background(
                    if (isFertilizationSelected) YellowAppColor else DarkGreenAppColor
                )
                .padding(5.dp)
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    isFertilizationSelected = true
                    isIrrigationSelected = false
                    isAllActionsSelected = false
                    onFertilizationSelected()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxHeight().size(24.dp),
                painter = painterResource(
                    id =
                    if (isFertilizationSelected) R.drawable.fertilization
                    else R.drawable.unselected_fertilization
                ),
                contentDescription = null
            )
        }

        Box(
            modifier = Modifier
                .background(
                    if (isIrrigationSelected) YellowAppColor else DarkGreenAppColor
                )
                .padding(5.dp)
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    isFertilizationSelected = false
                    isIrrigationSelected = true
                    isAllActionsSelected = false
                    onIrrigationSelected()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxHeight().size(24.dp),
                painter = painterResource(
                    id =
                    if (isIrrigationSelected) R.drawable.irrigation
                    else R.drawable.unselected_irrigation
                ),
                contentDescription = null
            )
        }

    }
}

@Preview
@Composable
private fun LandHistoryFiltersPreview() {
    LandHistoryFilters(
        onAllActionsSelected = {},
        onFertilizationSelected = {},
        onIrrigationSelected = {}
    )
}