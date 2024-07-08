package com.example.hapi.presentation.home.farmer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.home.common.HomeNotFoundWarning

@Composable
fun NoDetections(
    modifier: Modifier = Modifier,
    warning: String,
    warningDetails: String
) {

    Column (
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .size(70.dp),
            painter = painterResource(id = R.drawable.crop_profile),
            contentDescription = "home crop image"
        )
        HomeNotFoundWarning(warning = warning, warningDetails = warningDetails)
    }
}