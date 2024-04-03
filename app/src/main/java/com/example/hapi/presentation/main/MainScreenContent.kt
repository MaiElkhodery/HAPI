package com.example.hapi.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hapi.util.text.YellowMediumText

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowMediumText(
            text = text,
            modifier = Modifier.padding(bottom = 12.dp),
            size = 15
        )
        MainButton(text = buttonText) {
            onClick()
        }
    }
}
