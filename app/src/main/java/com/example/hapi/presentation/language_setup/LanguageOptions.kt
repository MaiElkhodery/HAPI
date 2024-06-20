package com.example.hapi.presentation.language_setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.ARABIC
import com.example.hapi.util.ENGLISH

@Composable
fun LanguageOptions(
    modifier: Modifier = Modifier,
    width: Dp,
    isEnglishSelected: Boolean,
    onLanguageSelected: (String) -> Unit
) {
   val smallTextSize = when {
        width <= 360.dp -> 16
        width in 360.dp..400.dp -> 20
        else -> 22
    }

    val largeTextSize = when {
        width <= 360.dp -> 28
        width in 360.dp..400.dp -> 32
        else -> 34
    }


    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = YellowAppColor,
            fontSize = smallTextSize.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_extrabold
                )
            ),
            text = stringResource(id = R.string.choose_language),
            textAlign = TextAlign.Center,
            lineHeight = 8.sp
        )
        Text(
            color = YellowAppColor,
            fontSize = smallTextSize.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_bold
                )
            ),
            text = stringResource(id = R.string.to_proceed),
            textAlign = TextAlign.Center,
            lineHeight = 8.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            modifier = Modifier.clickable {
                onLanguageSelected(ENGLISH)
            },
            color = if (isEnglishSelected) YellowAppColor else DarkGreenAppColor,
            fontSize = if (isEnglishSelected) largeTextSize.sp else smallTextSize.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_extrabold
                )
            ),
            text = stringResource(id = R.string.english),
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
        Text(
            modifier = Modifier.clickable {
                onLanguageSelected(ARABIC)
            },
            color = if (!isEnglishSelected) YellowAppColor else DarkGreenAppColor,
            fontSize = if (!isEnglishSelected) largeTextSize.sp else smallTextSize.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_bold
                )
            ),
            text = stringResource(id = R.string.arabic),
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}

@Preview
@Composable
private fun LanguageContentPreview() {
    LanguageOptions(
        width = 300.dp,
        isEnglishSelected = true,
    ) { }
}