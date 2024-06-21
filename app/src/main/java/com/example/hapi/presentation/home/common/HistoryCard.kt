package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.common.DarkGreenBlackText
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun VerticalHistoryCard(
    isLand: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(YellowAppColor)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(50.dp),
            painter = painterResource(
                id = if (isLand) {
                    R.drawable.land_history
                } else {
                    R.drawable.disease_history
                }
            ),
            contentDescription = null
        )
        DarkGreenBlackText(
            size = 15,
            text = if (isLand) stringResource(id = R.string.land_history)
            else stringResource(id = R.string.disease_history),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }

}

@Composable
fun HorizontalHistoryCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = YellowAppColor
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(55.dp),
                painter = painterResource(
                    id = R.drawable.disease_history
                ),
                contentDescription = null
            )
            DarkGreenBlackText(
                size = 15,
                text = stringResource(id = R.string.disease_history_horizontal),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}


@Preview
@Composable
fun HistoryCardPreview() {
    VerticalHistoryCard(
        isLand = true,
        onClick = {}
    )
//    HorizontalHistoryCard(
//        onClick = {}
//    )
}