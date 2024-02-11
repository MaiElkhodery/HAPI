package com.example.hapi.presentation.signup.landownersignup.ui.cropdetection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.data.model.Crop
import com.example.hapi.presentation.signup.common.ConfirmButton
import com.example.hapi.ui.theme.YellowAppColor

@Composable
fun FinalCropContent(
    modifier: Modifier,
    crop: Crop,
    onClick:()->Unit
){
    Column(
        modifier=modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ChosenCrop(crop = crop)
        Spacer(modifier = Modifier.padding(21.dp))
        ConfirmButton( text = stringResource(id = R.string.confirm_signup)) {
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
        ChosenCropText(text = stringResource(id = R.string.your_crop))

        Image(
            modifier = Modifier
                .size(95.dp)
                .padding(vertical = 6.dp),
            painter = painterResource(id = crop.image),
            contentDescription = "crop image"
        )
        ChosenCropText(text = crop.name)

    }
}

@Composable
private fun ChosenCropText(text: String) {
    Text(
        color = YellowAppColor,
        fontSize = 20.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_black
            )
        ), text = text, textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun ChosenCropPreview() {
    FinalCropContent(Modifier, crop = Crop(
        name = "TOMATO",
        image = R.drawable.tomato
    )){}
}