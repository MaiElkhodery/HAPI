package com.example.hapi.presentation.home.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.YellowExtraBoldText

@Composable
fun HistoryWarning(
    modifier: Modifier = Modifier,
    topMsg: Int,
    downMsg: Int,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.warning),
                contentDescription = null,
                tint = YellowAppColor
            )
        }

        YellowExtraBoldText(size = 15, text = stringResource(id = topMsg))

        Text(
            modifier = modifier,
            color = YellowAppColor,
            fontSize = 10.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_semibold
                )
            ),
            text = stringResource(id = downMsg),
            textAlign = TextAlign.Center,
            style = TextStyle(
                lineHeight = 9.sp
            )
        )
    }
}

@Preview
@Composable
private fun HistoryWarningPreview() {
    HistoryWarning(topMsg = R.string.not_detections, downMsg = R.string.click_on_camera)
}