package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.Crop
import com.example.hapi.presentation.common.YellowBoldText

@Composable
fun CropCollection(
    modifier: Modifier,
    onClick: (Crop) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        userScrollEnabled = true,
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center
    ) {
        items(Crop.entries) { crop ->

            CropItem(
                crop = crop,
                onClick = { onClick(crop) }
            )

        }
    }
}

@Composable
private fun CropItem(
    crop: Crop,
    onClick: (Crop) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 21.dp)
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val cropName = stringResource(id = getCropName(crop = crop.name))
        CropImageCard(imageId = getCropIcon(cropName)) {
            onClick(crop)
        }
        YellowBoldText(
            text = stringResource(id = getCropName(crop = crop.name)),
            size = 12,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Composable
private fun CropImageCard(
    imageId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = YellowAppColor
        )
    ) {

        Image(
            modifier = Modifier
                .padding(10.dp)
                .size(44.dp),
            painter = painterResource(id = imageId),
            contentDescription = "crop image"
        )

    }
}

@Preview
@Composable
private fun CropChooseContentPreview() {
    CropCollection(Modifier) {}
}




