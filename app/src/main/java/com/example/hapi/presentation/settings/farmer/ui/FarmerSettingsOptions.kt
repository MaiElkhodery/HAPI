package com.example.hapi.presentation.settings.farmer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.settings.common.SettingItem
import com.example.hapi.presentation.common.YellowExtraBoldText

@Composable
fun FarmerSettingsOptions(
    modifier: Modifier = Modifier,
    onLanguageClick: () -> Unit,
    onClearDetectionClick: () -> Unit,
    onHelpAndSupportClick: () -> Unit,
    onAboutUsClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        YellowExtraBoldText(
            size = 15,
            text = stringResource(id = R.string.app_settings)
        )
        SettingItem(
            text = stringResource(id = R.string.language),
            iconInt = R.drawable.language_icon
        ) {
            onLanguageClick()
        }

        SettingItem(
            text = stringResource(id = R.string.clear_detection),
            iconInt = R.drawable.data_and_storage_icon
        ) {
            onClearDetectionClick()
        }

        YellowExtraBoldText(
            size = 15,
            text = stringResource(id = R.string.more),
            modifier = Modifier.padding(top = 20.dp)
        )

        SettingItem(
            text = stringResource(id = R.string.help_and_support),
            iconInt = R.drawable.help_and_support_icon
        ) {
            onHelpAndSupportClick()
        }

        SettingItem(
            text = stringResource(id = R.string.about_us),
            iconInt = R.drawable.about_us_icon
        ) {
            onAboutUsClick()
        }

        YellowExtraBoldText(
            size = 15,
            text = stringResource(id = R.string.account_settings),
            modifier = Modifier.padding(top = 20.dp)
        )

        SettingItem(
            text = stringResource(id = R.string.delete_account),
            iconInt = R.drawable.delete_account_icon
        ) {
            onDeleteAccountClick()
        }

        SettingItem(
            text = stringResource(id = R.string.logout),
            iconInt = R.drawable.logout_icon
        ) {
            onLogoutClick()
        }


    }
}

@Preview
@Composable
private fun SettingsPreview() {
    FarmerSettingsOptions(
        onLanguageClick = {},
        onClearDetectionClick = {},
        onHelpAndSupportClick = {},
        onAboutUsClick = {},
        onDeleteAccountClick = {},
        onLogoutClick = {}
    )
}