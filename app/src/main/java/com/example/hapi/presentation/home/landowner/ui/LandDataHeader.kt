package com.example.hapi.presentation.home.landowner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.presentation.home.common.getCropName
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.WarningColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.YellowBoldText


@Composable
fun LandDataHeader(
    modifier: Modifier = Modifier,
    crop: String,
    waterTankLevel: Int,
    nTankLevel: Int,
    pTankLevel: Int,
    kTankLevel: Int,
) {
    val horizontalPadding = 4.dp

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (crop.isNotBlank())
            CropItem(crop = crop)
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            LandDataItem(
                modifier = Modifier
                    .weight(1f)
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = horizontalPadding),
                data = waterTankLevel,
                text = stringResource(id = R.string.water_tank)
            )
            LandDataItem(
                modifier = Modifier
                    .weight(1f)
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = horizontalPadding),
                data = nTankLevel, text = stringResource(id = R.string.n_tank)
            )

            LandDataItem(
                modifier = Modifier
                    .weight(1f)
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = horizontalPadding),
                data = pTankLevel,
                text = stringResource(id = R.string.p_tank)
            )
            LandDataItem(
                modifier = Modifier
                    .weight(1f)
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = horizontalPadding),
                data = kTankLevel,
                text = stringResource(id = R.string.k_tank)
            )
        }
    }
}

@Composable
private fun CropItem(
    crop: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        val cropName = stringResource(id = getCropName(crop))
        Image(
            modifier = Modifier
                .size(26.dp)
                .padding(end = 8.dp),
            painter = painterResource(
                id = getCropIcon(cropName)
            ),
            contentDescription = "crop icon"
        )
        YellowBoldText(
            text = cropName,
            size = 16
        )
    }
}

@Composable
fun LandDataItem(
    modifier: Modifier = Modifier,
    data: Int,
    text: String,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(
                if (data == 0) WarningColor else DarkGreenAppColor
            )
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = text,
            color = YellowAppColor,
            fontSize = 10.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_bold
                )
            ),
            textAlign = TextAlign.Center,
            style = TextStyle(
                lineHeight = 9.sp
            )
        )

        Text(
            modifier = modifier,
            color = YellowAppColor,
            fontSize = 14.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_black
                )
            ),
            text = "$data%",
            textAlign = TextAlign.Center,
            style = TextStyle(
                lineHeight = 9.sp
            )
        )

    }
}

@Preview
@Composable
fun LandDataHeaderPreview() {
    LandDataHeader(
        crop = "TOMATO",
        waterTankLevel = 50,
        nTankLevel = 20,
        pTankLevel = 0,
        kTankLevel = 40
    )
}

