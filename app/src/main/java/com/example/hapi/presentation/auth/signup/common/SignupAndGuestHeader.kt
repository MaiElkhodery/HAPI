package com.example.hapi.presentation.auth.signup.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun SignupAndGuestHeader(
    modifier: Modifier,
    topText: String,
    downText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.clickable {
                onClick()
            },
            painter = painterResource(id = R.drawable.nav_button),
            contentDescription = "logo"
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = topText,
                color = YellowAppColor,
                fontSize = 15.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_semibold
                    )
                )
            )
            Text(
                text = downText,
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
    SignupAndGuestHeader(Modifier, "set up", "your account"){}
}