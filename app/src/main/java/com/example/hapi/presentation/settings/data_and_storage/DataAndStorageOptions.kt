package com.example.hapi.presentation.settings.data_and_storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.settings.common.SettingItem
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun DataAndStorageOptions(
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp,
    onClickClearDetectionHistory: () -> Unit,
    onClickClearLandHistory: () -> Unit,
    onClickChangeCrop: () -> Unit
) {
    val fontSize = when {
        width < 360.dp -> 14
        width in 360.dp..400.dp -> 16
        else -> 18
    }
    val iconSize = when {
        height <= 600.dp -> 90.dp
        height in 600.dp..855.dp -> 100.dp
        else -> 110.dp
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.data_and_storage_icon),
                contentDescription = null,
                tint = YellowAppColor,
                modifier = Modifier.size(iconSize)
            )
        }

        SettingItem(
            text = stringResource(id = R.string.clear_detection),
            fontSize = fontSize,
            iconInt = R.drawable.reset_icon,
            modifier = Modifier.padding(bottom = 5.dp),
            onClick = onClickClearDetectionHistory
        )

        SettingItem(
            text = stringResource(id = R.string.clear_land_history),
            fontSize = fontSize,
            iconInt = R.drawable.reset_icon,
            modifier = Modifier.padding(bottom = 5.dp),
            onClick = onClickClearLandHistory
        )

        SettingItem(
            text = stringResource(id = R.string.change_crop),
            fontSize = fontSize,
            iconInt = R.drawable.reset_icon,
            modifier = Modifier.padding(bottom = 5.dp),
            onClick = onClickChangeCrop
        )

    }
}

@Preview
@Composable
private fun DataAndStorageContentPreview() {
    DataAndStorageOptions(
        width = 400.dp,
        height = 800.dp,
        onClickClearDetectionHistory = {},
        onClickClearLandHistory = {},
        onClickChangeCrop = {}
    )
}