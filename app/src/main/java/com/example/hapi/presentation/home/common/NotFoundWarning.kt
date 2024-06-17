package com.example.hapi.presentation.home.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.presentation.common.DarkGreenExtraBoldText
import com.example.hapi.presentation.common.YellowExtraBoldText

@Composable
fun HomeNotFoundWarning(
    modifier: Modifier = Modifier,
    warning: String,
    warningDetails: String,
    textColor: Color = DarkGreenAppColor,
    backgroundColor: Color = YellowAppColor,
    iconTintColor: Color = DarkGreenAppColor
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.warning),
                contentDescription = null,
                tint = iconTintColor,
            )
        }
        if (textColor == DarkGreenAppColor)
            DarkGreenExtraBoldText(
                size = 15,
                text = warning,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        else
            YellowExtraBoldText(
                size = 15,
                text = warning,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        Text(
            color = textColor,
            fontSize = 10.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_semibold
                )
            ),
            text = warningDetails,
            textAlign = TextAlign.Center,
            lineHeight = 12.sp
        )
    }
}