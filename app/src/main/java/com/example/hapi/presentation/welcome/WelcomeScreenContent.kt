package com.example.hapi.presentation.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hapi.util.YellowMediumText

@Composable
fun WelcomeScreenContent(
    modifier: Modifier = Modifier,
    isEnglish: Boolean,
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
        SignButton(text = buttonText,isEnglish = isEnglish){
            onClick()
        }
    }
}
