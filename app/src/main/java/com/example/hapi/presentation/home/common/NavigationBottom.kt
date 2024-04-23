package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.DarkGreenBlackText

@Composable
fun CustomNavigationBottom(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    var isHomeSelected by remember { mutableStateOf(true) }
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 37.dp)
    ) {
        val (home, camera, settings) = createRefs()

        createHorizontalChain(
            home, camera, settings,
            chainStyle = ChainStyle.SpreadInside
        )

        NavigationIcon(
            modifier = Modifier.constrainAs(home) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            },
            isSelected = isHomeSelected,
            text = stringResource(id = R.string.home),
            icon = Icons.Default.Home
        ) {
            isHomeSelected = true
            onHomeClick()
        }
        NavigationIcon(
            modifier = Modifier.constrainAs(settings) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            },
            isSelected = !isHomeSelected, text = stringResource(id = R.string.settings),
            icon = Icons.Default.Settings
        ) {
            isHomeSelected = false
            onSettingsClick()
        }
        CameraIcon(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .constrainAs(camera) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                    start.linkTo(home.end, margin = 20.dp)
                    end.linkTo(settings.start, margin = 20.dp)
                },
        ) {
            onCameraClick()
        }
    }
}

@Composable
private fun NavigationIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(bottom = 5.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(37.dp),
            imageVector = icon,
            contentDescription = null,
            tint = DarkGreenAppColor
        )
        if (isSelected) {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                imageVector = Icons.Default.Circle,
                contentDescription = null,
                tint = DarkGreenAppColor
            )
        } else {
            DarkGreenBlackText(size = 12, text = text)
        }
    }
}

@Composable
fun CustomNavigationBottomBackground(
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            rotate(degrees = 79F) {
                drawRect(
                    color = YellowAppColor,
                    topLeft = Offset(x = size.width / .73F, y = size.height / 3.1F),
                    size = size / 2F
                )
            }
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            rotate(degrees = 99F) {
                drawRect(
                    color = YellowAppColor,
                    topLeft = Offset(x = size.width / .73F, y = size.height / 2.8F),
                    size = size / 3F
                )
            }
        }
    }

}

@Composable
fun CameraIcon(
    modifier: Modifier = Modifier,
    iconColor: Color = YellowAppColor,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(if (iconColor == YellowAppColor) DarkGreenAppColor else YellowAppColor)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(70.dp)
                .padding(12.dp)
                .fillMaxSize(),
            imageVector = Icons.Default.CameraAlt,
            contentDescription = "camera icon",
            tint = iconColor
        )
    }
}

@Preview
@Composable
private fun CustomNavigationBottomPreview() {
    CustomNavigationBottomBackground()
    CustomNavigationBottom(Modifier, {}, {}) {}
}