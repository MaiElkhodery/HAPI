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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.common.ConfirmButton
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.presentation.home.common.getCropIcon

@Composable
fun FinalSelectedCropBox(
    modifier: Modifier = Modifier,
    width: Dp,
    height:Dp,
    imageSize: Dp,
    isEnglish: Boolean,
    crop: Int,
    onClick: () -> Unit
) {

    val cropName = stringResource(id = crop)

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FinalCrop(crop = cropName, width = width, imageSize = imageSize)

        Spacer(modifier = Modifier.padding(24.dp))

        ConfirmButton(
            width = width,
            height=height,
            text = stringResource(id = R.string.confirm_signup),
            isEnglish = isEnglish,
            onClick = onClick
        )
    }
}

@Composable
private fun FinalCrop(
    crop: String,
    width: Dp,
    imageSize: Dp
) {
    val fontSize = when {
        width < 360.dp -> 18
        width in 360.dp..400.dp -> 20
        else -> 22
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        YellowBlackText(text = stringResource(id = R.string.your_crop), size = fontSize)

        Image(
            modifier = Modifier
                .size(imageSize)
                .padding(vertical = 6.dp),
            painter = painterResource(id = getCropIcon(crop)),
            contentDescription = "crop image"
        )

        YellowBlackText(size = fontSize, text = crop)

    }
}

@Preview
@Composable
private fun ChosenCropPreview() {
    FinalSelectedCropBox(
        Modifier, isEnglish = false, crop = 0, width = 500.dp, imageSize = 90.dp, height = 800.dp
    ) {}
}