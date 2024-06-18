package com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.presentation.common.GreenBlackText
import com.example.hapi.presentation.common.YellowBoldText


@Composable
fun CropSelectionStrategyContent(
    modifier: Modifier = Modifier,
    onClickRecommendation: () -> Unit,
    onClickHaveCrop: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecommendationRaw(
            cardText = stringResource(id = R.string.recommend_crop),
            description = stringResource(id = R.string.use_recommendation)
        ) {
            onClickRecommendation()
        }
        RecommendationRaw(
            cardText = stringResource(id = R.string.have_crop),
            description = stringResource(id = R.string.choose_crop)
        ) {
            onClickHaveCrop()
        }
    }
}

@Composable
private fun RecommendationRaw(
    cardText: String,
    description: String,
    onCardClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RecommendationCard(
            modifier = Modifier.weight(1f),
            text = cardText
        ) {
            onCardClick()
        }
        YellowBoldText(
            text = description,
            size = 13,
            modifier = Modifier.weight(1.2f)
        )

    }
}


@Composable
private fun RecommendationCard(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(end = 11.dp)
            .height(IntrinsicSize.Max)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = YellowAppColor
        )
    ) {
        GreenBlackText(
            size = 15,
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 16.dp, horizontal = 2.dp)
        )
    }
}


@Preview
@Composable
private fun ContentPreview() {
    CropSelectionStrategyContent(Modifier, {}) {}
}