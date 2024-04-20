package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.util.YellowBlackText

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    imageId: Int,
    username: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(48.dp)
                .padding(bottom = 3.dp)
                .clip(shape = CircleShape),
            painter = painterResource(id = imageId),
            contentDescription = "profile image"
        )
        YellowBlackText(
            size = 14,
            text = stringResource(id = R.string.welcome) + username
        )

    }
}

@Preview
@Composable
private fun HomeHeaderPreview() {
    HomeHeader(imageId = R.drawable.farmer, username = "John Doe")
}