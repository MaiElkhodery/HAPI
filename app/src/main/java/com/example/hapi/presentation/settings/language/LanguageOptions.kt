package com.example.hapi.presentation.settings.language

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.hapi.R

@Composable
fun LanguageOption(
    isSelected: Boolean,
    textId: Int,
    selectedColor: Color,
    unselectedColor: Color,
    selectedFontSize: Int,
    unselectedFontSize: Int,
    onCLick: ()->Unit
) {
    Text(
        modifier = Modifier.clickable { onCLick() },
        color = if (isSelected) selectedColor else unselectedColor,
        fontSize = if (isSelected) selectedFontSize.sp else unselectedFontSize.sp,
        fontFamily = FontFamily(Font(R.font.poppins_black)),
        text = stringResource(id = textId),
        textAlign = TextAlign.Center
    )
}