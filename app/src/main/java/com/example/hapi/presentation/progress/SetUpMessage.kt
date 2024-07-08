package com.example.hapi.presentation.progress

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.common.NextButton
import com.example.hapi.presentation.common.YellowBlackText

@Composable
fun SetupMessage(
    message: String,
    fontSize: Int,
    iconSize: Dp,
    onlClickContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowBlackText(size = fontSize, text = message, modifier = Modifier.padding(bottom = 8.dp))

        NextButton(iconSize, onlClickContinue)
    }
}


@Preview
@Composable
fun SetupMessagePreview() {
    SetupMessage(
        stringResource(id = R.string.account_setup),
        24, 24.dp
    ) {}
}
