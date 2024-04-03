package com.example.hapi.presentation.auth.signup.landownersignup.choose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.presentation.home.common.getCropIcon
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.Crop

@Composable
fun CropChooseContent(
    modifier: Modifier,
    onClick: (Crop) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        userScrollEnabled = true,
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(Crop.entries) { crop ->
            CropItem(
                crop = crop
            ) {
                onClick(crop)
            }
        }
    }
}

@Composable
private fun CropItem(
    crop: Crop,
    onClick: (Crop) -> Unit
) {
    Column(
        modifier = Modifier.padding(bottom = 8.dp)
            .clickable {
                onClick(crop)
            }
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CropImageCard(imageId = getCropIcon(crop))
        CropName(title = crop.name)
    }
}

@Composable
private fun CropImageCard(
    imageId: Int
) {
    Card(
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = YellowAppColor
        )
    ) {
        Box(
            modifier = Modifier
                .padding(11.dp)
        ) {
            Image(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = imageId),
                contentDescription = "crop image"
            )
        }
    }
}

@Composable
private fun CropName(
    title: String
) {

    Text(
        modifier = Modifier
            .padding(top = 12.dp),
        color = YellowAppColor,
        fontSize = 11.sp,
        fontFamily = FontFamily(
            Font(
                R.font.poppins_bold
            )
        ),
        text = title,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun CropChooseContentPreview() {
    CropChooseContent(Modifier) {}
}




