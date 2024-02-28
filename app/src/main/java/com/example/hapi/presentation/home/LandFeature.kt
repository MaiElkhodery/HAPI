package com.example.hapi.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.FeatureInfo
import com.example.hapi.util.LandAction
import com.example.hapi.util.text.DarkGreenBlackText
import com.example.hapi.util.text.DarkGreenExtraBoldText

@Composable
fun LastLandActionContent(
    action: LandAction,
    date: String,
    time: String
) {
    Column(
        modifier = Modifier
            .background(YellowAppColor)
            .padding(vertical = 16.dp)
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
            .padding(horizontal = 8.dp, vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(44.dp),
            painter = painterResource(
                id =
                if (action == LandAction.FERTILIZATION) R.drawable.fertilization
                else R.drawable.irrigation
            ),
            contentDescription = null
        )
        DarkGreenBlackText(
            modifier = Modifier.padding(vertical = 7.dp),
            size = 12,
            text = action.name
        )
        Row {
            LastLandActionInfo(
                data = date,
                actionTime = FeatureInfo.DATE,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            LastLandActionInfo(
                data = time,
                actionTime = FeatureInfo.TIME,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LastLandActionInfo(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    actionTime: FeatureInfo,
    data: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(
                id =
                when (actionTime) {
                    FeatureInfo.TIME -> R.drawable.time
                    FeatureInfo.USER -> R.drawable.user
                    else -> R.drawable.date
                }
            ),
            contentDescription = "date",
            tint = DarkGreenAppColor
        )
        DarkGreenExtraBoldText(
            modifier = Modifier.padding(start = 5.dp),
            size = 12, text = data
        )
    }
}


@Preview
@Composable
private fun LandActionPreview() {
    LastLandActionContent(
        action = LandAction.FERTILIZATION,
        date = "12/12/2021",
        time = "12:00"
    )
}

