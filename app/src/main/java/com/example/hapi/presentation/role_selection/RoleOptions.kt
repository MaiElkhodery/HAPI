package com.example.hapi.presentation.role_selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.common.GreenBlackText
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun RoleOptions(
    modifier: Modifier = Modifier,
    fontSize: Int,
    imageSize: Dp,
    onClickLandowner: () -> Unit,
    onClickFarmer: () -> Unit
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowBlackText(
            size = fontSize,
            text = stringResource(id = R.string.are_you),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RoleOptionCard(
                fontSize = fontSize,
                imageSize = imageSize,
                identityName = stringResource(id = R.string.landowner),
                roleImageId = R.drawable.landowner,
                modifier = Modifier.weight(1f)
            ) {
                onClickLandowner()
            }
            Spacer(modifier = Modifier.weight(.1f))
            RoleOptionCard(
                fontSize = fontSize,
                imageSize = imageSize,
                identityName = stringResource(id = R.string.farmer),
                roleImageId = R.drawable.farmer,
                modifier = Modifier.weight(1f)
            ) {
                onClickFarmer()
            }
        }

    }
}

@Composable
private fun RoleOptionCard(
    identityName: String,
    fontSize: Int,
    imageSize: Dp,
    roleImageId: Int,
    modifier: Modifier,
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GreenBlackText(size = fontSize, text = identityName)
            Image(
                modifier = Modifier
                    .size(imageSize),
                painter = painterResource(id = roleImageId),
                contentDescription = null
            )
        }
    }
}

@Composable
fun CropImage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.crop_profile),
            contentScale = ContentScale.Fit,
            contentDescription = "crop"
        )
    }
}

@Preview
@Composable
fun IdentitySelectionPreview() {
    RoleOptions(
        fontSize = 16,
        imageSize = 90.dp,
        onClickLandowner = {},
        onClickFarmer = {}
    )
}