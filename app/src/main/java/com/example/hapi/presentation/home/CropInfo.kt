package com.example.hapi.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.hapi.data.model.Crop
import com.example.hapi.data.model.CropType
import com.example.hapi.util.text.YellowBlackText

@Composable
fun CropInfo(
    crop: Crop,
    n: String,
    p: String,
    k: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp,horizontal = 36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CropRow(
            crop = crop
        )
        CropNutrientInfo(
            n = n,
            p = p,
            k = k
        )
    }
}

@Composable
fun CropNutrientInfo(n: String, p: String, k: String) {
   Column {
       YellowBlackText(text = stringResource(id = R.string.n_p_k), size = 13)
       YellowBlackText(text = "$n : $p : $k", size = 13)
   }
}

@Composable
fun CropRow(
    modifier: Modifier = Modifier,
    crop: Crop
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    )  {

        Image(
            modifier = Modifier
                .size(58.dp)
                .padding(vertical = 6.dp),
            painter = painterResource(id = crop.imageId),
            contentDescription = "crop image"
        )
        YellowBlackText(size = 13, text = crop.type.name)

    }

}

@Preview
@Composable
fun CropInfoPreview() {
    CropInfo(
        crop = Crop(
            type = CropType.CORN,
            imageId = R.drawable.corn
        ),
        n = "1",
        p = "2",
        k = "8"
    )
}