package com.example.hapi.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    text: String,
    buttonText:String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MediumYellowText(
            text = text,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        MainButton(text = buttonText) {
            onClick()
        }
    }
}

@Composable
fun MediumYellowText(
    text: String,
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        color = YellowAppColor,
        fontSize = 15.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_medium
            )
        )
    )
}