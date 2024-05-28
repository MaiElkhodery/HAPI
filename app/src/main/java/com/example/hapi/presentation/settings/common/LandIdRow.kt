package com.example.hapi.presentation.settings.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.YellowExtraBoldText

@Composable
fun LandIdRow(
    landId: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.farm),
            contentDescription = null,
            tint = YellowAppColor,
            modifier = Modifier.padding(end = 6.dp)
        )
        YellowExtraBoldText(size = 15, text = stringResource(id = R.string.land_id) + " : "+ landId)
    }
}

@Preview
@Composable
fun LandIdRowPreview() {
    LandIdRow(landId = "jiuhygfc")
}