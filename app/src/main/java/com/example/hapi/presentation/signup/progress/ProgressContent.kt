package com.example.hapi.presentation.signup.progress

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.presentation.signup.common.GreenBlackText
import com.example.hapi.presentation.signup.common.YellowBlackText
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun Crops(
    modifier: Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.crop_profile),
            contentDescription = "crop"
        )
    }
}

@Composable
fun SetupMessage(
    message: String,
    onlClickContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowBlackText(size = 20, text = message, modifier = Modifier.padding(bottom = 8.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(YellowAppColor)
                .clickable {
                    onlClickContinue()
                }
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 18.dp)
                    .size(35.dp),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = GreenAppColor
            )
        }
    }
}

@Composable
fun IdentitySelection(
    onClickLandowner: () -> Unit,
    onclickFarmer: () -> Unit
) {
    Column(
        modifier = Modifier.wrapContentSize(),
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
            IdentityCard(
                identityName = stringResource(id = R.string.farmer),
                identityImageId = R.drawable.farmer,
                modifier = Modifier.weight(1f)
            ) {
                onclickFarmer()
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
            GreenBlackText(
                size = 16, text = identityName, modifier = Modifier
                    .padding(top = 10.dp)
            )
            Image(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(95.dp),
                painter = painterResource(id = identityImageId),
                contentDescription = null
            )
        }
    }
}


@Preview
@Composable
fun SetupMessagePreview() {
    SetupMessage(stringResource(id = R.string.account_setup)) {}
}

@Preview
@Composable
fun IdentitySelectionPreview() {
    IdentitySelection({}, {})
}