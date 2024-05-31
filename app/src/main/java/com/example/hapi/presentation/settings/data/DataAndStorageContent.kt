package com.example.hapi.presentation.settings.data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.settings.common.SettingItem
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun DataAndStorageContent(
    modifier: Modifier = Modifier,
    onClickClearDetectionHistory: () -> Unit,
    onClickClearLandHistory: () -> Unit,
    onClickChangeCrop: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.data_and_storage_icon),
                contentDescription = null,
                tint = YellowAppColor,
                modifier = Modifier.size(100.dp)
            )
        }

        SettingItem(
            text = stringResource(id = R.string.clear_detcetion_history),
            iconInt = R.drawable.reset_icon,
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            onClickClearDetectionHistory()
        }

        SettingItem(
            text = stringResource(id = R.string.clear_land_history),
            iconInt = R.drawable.reset_icon,
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            onClickClearLandHistory()
        }

        SettingItem(
            text = stringResource(id = R.string.change_crop),
            iconInt = R.drawable.reset_icon,
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
           onClickChangeCrop()
        }

    }
}

@Preview
@Composable
private fun DataAndStorageContentPreview() {
    DataAndStorageContent(
        onClickClearDetectionHistory = {},
        onClickClearLandHistory = {},
        onClickChangeCrop = {}
    )
}