package com.example.hapi.presentation.auth.signup.landownersignup.finalcrop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.ConfirmButton
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.util.Crop
import com.example.hapi.util.YellowBlackText

@Composable
fun FinalCropContent(
    modifier: Modifier,
    crop: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChosenCrop(crop = Crop.valueOf(crop.uppercase()))
        Spacer(modifier = Modifier.padding(21.dp))
        ConfirmButton(text = stringResource(id = R.string.confirm_signup)) {
            onClick()
        }
    }
}

@Composable
private fun ChosenCrop(
    crop: Crop
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YellowBlackText(text = stringResource(id = R.string.your_crop), size = 20)

        Image(
            modifier = Modifier
                .size(95.dp)
                .padding(vertical = 6.dp),
            painter = painterResource(id = getCropIcon(crop)),
            contentDescription = "crop image"
        )
        YellowBlackText(size = 20, text = crop.name)

    }
}

@Preview
@Composable
private fun ChosenCropPreview() {
    FinalCropContent(
        Modifier, "WHEAT"
    ) {}
}