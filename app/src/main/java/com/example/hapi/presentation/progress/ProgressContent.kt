package com.example.hapi.presentation.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.auth.signup.common.YellowBlackText
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun SetupMessage(
    message: String,
    onlClickContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowBlackText(size = 20, text = message, modifier = Modifier.padding(bottom = 8.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(YellowAppColor)
                .clickable {
                    onlClickContinue()
                }
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .size(35.dp),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = GreenAppColor
            )
        }
    }
}


@Preview
@Composable
fun SetupMessagePreview() {
    SetupMessage(stringResource(id = R.string.account_setup)) {}
}
