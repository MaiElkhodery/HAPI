package com.example.hapi.presentation.auth.signup.landownersignup.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R

@Composable
fun LotusRow(
    highlightedLotusPos: Short,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until 4) {
            LotusImage(
                isHighlighted = (i.toShort() == highlightedLotusPos)
            )
        }
    }
}

@Composable
private fun LotusImage(
    isHighlighted: Boolean
) {
    val imageId = if (isHighlighted) R.drawable.top_lotus else R.drawable.lotus2

    Image(
        modifier = Modifier
            .padding(3.dp)
            .size(30.dp),
        painter = painterResource(id = imageId),
        contentDescription = "lotus image",
    )

}

@Preview
@Composable
private fun LotusRowPreview() {
    LotusRow(3,Modifier)
}