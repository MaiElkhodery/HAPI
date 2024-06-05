package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.FeatureInfo
import com.example.hapi.util.LandAction
import com.example.hapi.util.DarkGreenBlackText

@Composable
fun LastLandActionCard(
    modifier: Modifier = Modifier,
    action: LandAction,
    date: String,
    time: String
) {
    Column(
        modifier = modifier
            .background(YellowAppColor)
            .padding(vertical = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DarkGreenBlackText(
            modifier = Modifier.padding(bottom = 5.dp),
            size = 15,
            text = stringResource(id = R.string.last_action)
        )

        LandAction(
            action = action,
            date = date,
            time = time
        )
    }
}

@Composable
fun LandAction(
    action: LandAction,
    date: String,
    time: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(YellowAppColor)
            .padding(horizontal = 8.dp, vertical = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(
                id =
                if (action == LandAction.FERTILIZATION) R.drawable.fertilization
                else R.drawable.irrigation
            ),
            contentDescription = null
        )
        DarkGreenBlackText(
            modifier = Modifier.padding(vertical = 4.dp),
            size = 12,
            text = stringResource(id = LandActionName(name = action.name))
        )
        Row {
            ActionInfo(
                data = date,
                action = FeatureInfo.DATE,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            ActionInfo(
                data = time,
                action = FeatureInfo.TIME,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun ActionInfo(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    color: Color = DarkGreenAppColor,
    fontSize: Int = 12,
    action: FeatureInfo,
    data: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(if (fontSize <= 12) 20.dp else 26.dp),
            painter = painterResource(
                id =
                when (action) {
                    FeatureInfo.TIME -> R.drawable.time
                    FeatureInfo.USER -> R.drawable.user
                    else -> R.drawable.date
                }
            ),
            contentDescription = "date",
            tint = color
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            color = color,
            fontSize = fontSize.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_extrabold
                )
            ),
            text = data,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
private fun LandActionPreview() {
    LastLandActionCard(
        action = LandAction.FERTILIZATION,
        date = "12/12/2021",
        time = "12:00"
    )
}

