package com.example.hapi.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.text.GreenBlackText

@Composable
fun HistoryCard(
    type: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 7.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = YellowAppColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(95.dp),
                painter = painterResource(
                    id = if (type == "land") {
                        R.drawable.land_history
                    } else {
                        R.drawable.disease_history
                    }
                ),
                contentDescription = null
            )

            GreenBlackText(
                size = 16,
                text = if (type == "land") stringResource(id = R.string.land_history)
                else stringResource(id = R.string.disease_history),
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun HistoryCardPreview() {
    HistoryCard(
        type = "land",
        onClick = {}
    )
}