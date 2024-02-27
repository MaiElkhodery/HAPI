package com.example.hapi.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.example.hapi.presentation.auth.signup.common.YellowBlackText
import com.example.hapi.ui.theme.DarkGreenAppColor

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    imageId: Int,
    username: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = DarkGreenAppColor)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        UserProfile(
            imageId = imageId,
            username = username
        )
        CameraCaptureBox{
            onClick()
        }
    }
}

@Composable
fun UserProfile(
    modifier: Modifier = Modifier,
    imageId: Int,
    username: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .padding(end = 3.dp)
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
    HomeHeader(imageId = R.drawable.farmer, username = "John Doe") {}
}