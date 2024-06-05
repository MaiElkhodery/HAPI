package com.example.hapi.presentation.settings.support

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.GreenBlackText
import com.example.hapi.util.YellowBlackText

@Composable
fun HelpAndSupportContent(
    modifier: Modifier = Modifier,
    onFQAClick: () -> Unit,
    onCallNowClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.help_and_support_icon),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(165.dp)
            )
        }

        helpAndSupportTextAndButton(
            text = stringResource(id = R.string.fqa_text),
            btnText = stringResource(id = R.string.fqa),
            imageId = R.drawable.fqa_icon
        ) {
            onFQAClick()
        }

        helpAndSupportTextAndButton(
            text = stringResource(id = R.string.call_now_text),
            btnText = stringResource(id = R.string.call_now),
            imageId = R.drawable.call_icon
        ) {
            onCallNowClick()
        }
    }
}

@Composable
fun helpAndSupportTextAndButton(
    modifier: Modifier = Modifier,
    text: String,
    btnText: String,
    imageId: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        YellowBlackText(
            size = 13,
            text = text,
            modifier = Modifier.weight(1f),
            align = TextAlign.Start
        )

        ButtonWithEndIcon(
            text = btnText,
            imageId = imageId,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            onClick()
        }
    }
}

@Composable
fun ButtonWithEndIcon(
    modifier: Modifier = Modifier,
    text: String,
    imageId: Int,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(4.dp)),
    ) {

        GreenTextBox(
            text = text, modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
        )

        ButtonIcon(
            imageId = imageId,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { onClick() }

    }

}

@Composable
private fun GreenTextBox(
    text: String,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(YellowAppColor)
            .fillMaxHeight()
            .padding(9.dp),
        contentAlignment = Alignment.Center
    ) {

        GreenBlackText(size = 16, text = text)

    }

}

@Composable
fun ButtonIcon(
    modifier: Modifier = Modifier,
    imageId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(DarkGreenAppColor)
            .fillMaxHeight()
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .padding( 5.dp)
                .size(28.dp),
            painter = painterResource(id = imageId),
            contentDescription = null,
            tint = YellowAppColor
        )
    }
}

@Preview
@Composable
private fun HelpAndSupportContentPreview() {
    HelpAndSupportContent(
        onFQAClick = {},
        onCallNowClick = {}
    )
}