package com.example.hapi.presentation.auth.signup.landownersignup.detection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.presentation.signup.common.GreenBlackText
import com.example.hapi.ui.theme.YellowAppColor


@Composable
fun CropDetectionContent(
    modifier: Modifier,
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
            .padding(bottom = 26.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RecommendationCard(
            modifier = Modifier.weight(1.2f),
            text = cardText
        ) {
            onCardClick()
        }
        DescriptionText(
            modifier = Modifier.weight(1.4f),
            text = description
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
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = YellowAppColor
        )
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 8.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            GreenBlackText(size = 15, text = text)

        }
    }
}

@Composable
private fun DescriptionText(
    modifier: Modifier,
    text: String
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            color = YellowAppColor,
            fontSize = 13.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_bold
                )
            ),
            text = text,
            textAlign = TextAlign.Start
        )
    }
}

@Preview
@Composable
private fun ContentPreview() {
    CropDetectionContent(Modifier, {}) {}
}