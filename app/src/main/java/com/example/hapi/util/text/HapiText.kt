package com.example.hapi.util.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.WarningColor
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

@Composable
fun GreenBoldText(
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
                R.font.poppins_bold
            )
        ),
        text = text,
        textAlign = TextAlign.Center
    )
}
@Composable
fun DarkGreenBoldText(
    modifier: Modifier = Modifier,
    size: Int,
    text: String
) {
    Text(
        modifier = modifier,
        color = DarkGreenAppColor,
        fontSize = size.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_bold
            )
        ),
        text = text,
        textAlign = TextAlign.Start
    )
}
@Composable
fun DarkGreenBlackText(
    modifier: Modifier = Modifier,
    size: Int,
    text: String
) {
    Text(
        modifier = modifier,
        color = DarkGreenAppColor,
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
fun RedBlackText(
    modifier: Modifier = Modifier,
    size: Int,
    text: String
) {
    Text(
        modifier = modifier,
        color = WarningColor,
        fontSize = size.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_black
            )
        ),
        text = text,
        textAlign = TextAlign.Start
    )
}

@Composable
fun GreenExtraBoldText(
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
                R.font.poppins_extrabold
            )
        ),
        text = text,
        textAlign = TextAlign.Center
    )
}
@Composable
fun DarkGreenExtraBoldText(
    modifier: Modifier = Modifier,
    size: Int,
    text: String
) {
    Text(
        modifier = modifier,
        color = DarkGreenAppColor,
        fontSize = size.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_extrabold
            )
        ),
        text = text,
        textAlign = TextAlign.Center
    )
}