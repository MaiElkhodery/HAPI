package com.example.hapi.presentation.settings.farmers.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.home.common.HomeNotFoundWarning
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.presentation.common.GreenBlackText
import com.example.hapi.presentation.common.YellowExtraBoldText

@Composable
fun NoFarmers(
    modifier: Modifier = Modifier,
    width:Dp
) {
    val imageSize = when {
        width <= 360.dp -> 150
        width in 360.dp..400.dp -> 180
        else -> 200
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        FarmersImage(imageSize)
        HomeNotFoundWarning(
            warning = stringResource(id = R.string.no_farmers_yet),
            warningDetails = stringResource(id = R.string.no_farmers_yet_details),
            textColor = YellowAppColor,
            backgroundColor = GreenAppColor,
            iconTintColor = YellowAppColor,
            modifier = Modifier.padding(top = 28.dp)
        )
    }
}

@Composable
fun FarmersGridList(
    farmers: List<String>,
    width: Dp,
    modifier: Modifier = Modifier
) {
    val fontSize = when {
        width <= 360.dp -> 13
        width in 360.dp..400.dp -> 15
        else -> 17
    }
    val imageSize = when {
        width <= 360.dp -> 150
        width in 360.dp..400.dp -> 180
        else -> 200
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FarmersImage(imageSize)
        YellowExtraBoldText(size = fontSize,
            text = stringResource(id = R.string.no_of_farmers)+ "${farmers.size}",
            modifier = Modifier.padding(11.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(10.dp)
        ) {
            items(farmers.size) {
                FarmersListItem(
                    farmer = farmers[it],
                    fontSize = fontSize-3,
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 14.dp)
                )
            }
        }
    }
}

@Composable
fun FarmersListItem(
    modifier: Modifier = Modifier,
    fontSize:Int,
    farmer: String
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(2.dp))
            .background(YellowAppColor)
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.farmer_icon),
            contentDescription = null,
            tint = GreenAppColor,
            modifier = Modifier.padding(6.dp)
        )
        GreenBlackText(size = fontSize, text = farmer)
    }
}

@Composable
fun FarmersImage(
    imageSize:Int
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier=Modifier.size(imageSize.dp),
            painter = painterResource(id = R.drawable.farmers),
            contentDescription = "Farmers",
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
private fun FarmersPreview() {
    FarmersGridList(
        width = 360.dp,
        farmers = listOf(
            "Farmer 1",
            "Farmer 2",
            "Farmer 3",
            "Farmer 4",
            "Farmer 5",
            "Farmer 6"
        )
    )
}