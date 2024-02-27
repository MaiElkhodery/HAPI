package com.example.hapi.presentation.auth.signup.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun YellowBlackText(
    modifier: Modifier = Modifier,
    size: Int,
    text: String
) {
    Text(
        modifier = modifier,
        color = YellowAppColor,
        fontSize = size.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_black
            )
        ),
        text = text,
        textAlign = TextAlign.Center
    )
}
@Composable
fun GreenBlackText(
    modifier: Modifier = Modifier,
    size: Int,
    text: String
) {
    Text(
        modifier = modifier,
        color = GreenAppColor,
        fontSize = size.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_black
            )
        ),
        text = text,
        textAlign = TextAlign.Center
    )
}