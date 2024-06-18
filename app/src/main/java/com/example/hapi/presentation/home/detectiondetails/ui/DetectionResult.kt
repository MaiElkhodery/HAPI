package com.example.hapi.presentation.home.detectiondetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.presentation.settings.support.ButtonWithEndIcon
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.common.YellowBoldText

@Composable
fun DiseasedResult(
    modifier: Modifier = Modifier,
    diseaseName: String,
    certainty: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = modifier,
            color = YellowAppColor,
            fontSize = 24.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_black
                )
            ),
            text = "$diseaseName\n" + stringResource(id = R.string.disease_result),
            textAlign = TextAlign.Center,
            lineHeight = 30.sp
        )

        CertaintyText(certainty = certainty)

        ButtonWithEndIcon(
            text = stringResource(id = R.string.learn_more),
            imageId = R.drawable.learn_more_icon,
            modifier = Modifier
                .padding(vertical = 26.dp)
                .width(180.dp),
        ) {
            onClick()
        }
    }
}

@Composable
fun HealthyResult(
    modifier: Modifier = Modifier,
    certainty: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YellowBlackText(
            size = 24,
            text = stringResource(id = R.string.healthy),
            align = TextAlign.Center
        )
        CertaintyText(certainty = certainty)
    }
}

@Composable
fun CertaintyText(
    certainty: String
) {
    Row(
        modifier = Modifier.padding(top = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.warning),
            contentDescription = null,
            tint = YellowAppColor,
            modifier = Modifier
                .size(18.dp)
                .padding(end = 4.dp)
        )
        YellowBoldText(
            text = stringResource(id = R.string.with_a),
            size = 10,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        YellowBlackText(size = 14, text = " $certainty% ")
        YellowBoldText(
            text = stringResource(id = R.string.result_certainty),
            size = 10,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun DiseasesResultPreview() {
    DiseasedResult(
        diseaseName = "EARLY BLIGHT",
        certainty = "50"
    ) {}
}