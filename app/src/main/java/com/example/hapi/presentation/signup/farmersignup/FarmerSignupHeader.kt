package com.example.hapi.presentation.signup.farmersignup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun FarmerSignupHeader(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.setting_up),
                color = YellowAppColor,
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_semibold
                    )
                )
            )
            Text(
                text = stringResource(id = R.string.your_account),
                color = YellowAppColor,
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_black
                    )
                )
            )
        }


    }
}

@Preview
@Composable
private fun FarmerSignupHeaderPreview() {
    FarmerSignupHeader(Modifier)
}