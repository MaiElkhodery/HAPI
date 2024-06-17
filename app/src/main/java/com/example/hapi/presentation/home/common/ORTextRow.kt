package com.example.hapi.presentation.home.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.common.YellowMediumText

@Composable
fun ORTextRow(
    modifier: Modifier = Modifier,
    text: String,
    iconId: Int = R.drawable.gallery_icon,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.Center
    ) {
        YellowMediumText(
            text = stringResource(id = R.string.or),
            modifier = Modifier.padding(end = 3.dp),
            size = 15
        )
        YellowBlackText(size = 15, text = text)
        Icon(
            modifier = Modifier.padding(start = 3.dp),
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = YellowAppColor
        )
    }
}