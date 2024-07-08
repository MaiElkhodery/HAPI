package com.example.hapi.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 15,
    iconId: Int = R.drawable.gallery_icon,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        YellowMediumText(
            text = stringResource(id = R.string.or),
            modifier = Modifier.padding(end = 3.dp),
            size = fontSize
        )
        YellowBlackText(size = fontSize, text = text)
        Icon(
            modifier = Modifier.size(18.dp).padding(start = 4.dp),
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = YellowAppColor
        )
    }
}