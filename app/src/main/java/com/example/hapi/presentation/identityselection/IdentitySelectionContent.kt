package com.example.hapi.presentation.identityselection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.GreenBlackText

@Composable
fun IdentitySelection(
    modifier: Modifier = Modifier,
    onClickLandowner: () -> Unit,
    onClickFarmer: () -> Unit
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            color = YellowAppColor,
            fontSize = 16.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_black
                )
            ),
            text = stringResource(id = R.string.are_you),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IdentityCard(
                identityName = stringResource(id = R.string.landowner),
                identityImageId = R.drawable.landowner,
                modifier = Modifier.weight(1f)
            ) {
                onClickLandowner()
            }
            Spacer(modifier = Modifier.width(8.dp))
            IdentityCard(
                identityName = stringResource(id = R.string.farmer),
                identityImageId = R.drawable.farmer,
                modifier = Modifier.weight(1f)
            ) {
                onClickFarmer()
            }
        }

    }
}

@Composable
private fun IdentityCard(
    identityName: String,
    identityImageId: Int,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(5.dp)
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
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GreenBlackText(
                size = 16, text = identityName, modifier = Modifier
            )
            Image(
                modifier = Modifier
                    .size(95.dp),
                painter = painterResource(id = identityImageId),
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
        modifier = modifier,
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
    IdentitySelection(
        onClickLandowner = {},
        onClickFarmer = {}
    )
//    CropImage()
}