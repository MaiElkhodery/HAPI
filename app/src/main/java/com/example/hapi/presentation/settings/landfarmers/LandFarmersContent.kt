package com.example.hapi.presentation.settings.landfarmers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.presentation.home.common.NotFoundWarning
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.GreenBlackText
import com.example.hapi.util.YellowExtraBoldText

@Composable
fun NoFarmers(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FarmersImage()
        NotFoundWarning(
            warning = stringResource(id = R.string.no_farmers_yet),
            warningDetails = stringResource(id = R.string.no_farmers_yet_details),
            textColor = YellowAppColor,
            backgroundColor = GreenAppColor,
            iconTintColor = YellowAppColor,
            modifier = Modifier.padding(top = 44.dp)
        )
    }
}

@Composable
fun FarmersGridList(
    farmers: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FarmersImage()
        YellowExtraBoldText(size = 15,
            text = "NUMBER OF FARMERS : ${farmers.size}",
            modifier = Modifier.padding(11.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(12.dp)
        ) {
            items(farmers.size) {
                FarmersListItem(
                    farmer = farmers[it],
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 14.dp)
                )
            }
        }
    }
}

@Composable
fun FarmersListItem(
    modifier: Modifier = Modifier,
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
        GreenBlackText(size = 12, text = farmer)
    }
}

@Composable
fun FarmersImage() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
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