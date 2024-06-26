package com.example.hapi.presentation.home.common

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hapi.R
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.presentation.common.DarkGreenBlackText
import com.example.hapi.util.Tab

@Composable
fun CustomNavigationBottom(
    modifier: Modifier = Modifier,
    selectedTab: Tab,
    onHomeClick: () -> Unit,
    onCameraClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    var isHomeSelected by remember { mutableStateOf(true) }
    var isSettingsSelected by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(GreenAppColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.nav_bottom_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp)
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 46.dp)
        ) {
            val (home, camera, settings) = createRefs()

            CameraIcon(
                modifier = Modifier
                    .padding(bottom = 22.dp)
                    .constrainAs(camera) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                    },
            ) {
                isHomeSelected = false
                isSettingsSelected = false
                onCameraClick()
            }

            NavigationIcon(
                modifier = Modifier.constrainAs(home) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                isSelected  = selectedTab == Tab.HOME,
                text = stringResource(id = R.string.home),
                icon = Icons.Default.Home
            ) {
                isHomeSelected = true
                isSettingsSelected = false
                onHomeClick()
            }
            NavigationIcon(
                modifier = Modifier.constrainAs(settings) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
                isSelected = selectedTab == Tab.SETTINGS,
                text = stringResource(id = R.string.settings),
                icon = Icons.Default.Settings
            ) {
                isHomeSelected = false
                isSettingsSelected = true
                onSettingsClick()
            }

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
            .padding(bottom = 3.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(26.dp),
            imageVector = icon,
            contentDescription = null,
            tint = DarkGreenAppColor
        )
        if (isSelected) {
            Icon(
                modifier = Modifier
                    .size(15.dp),
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
                .size(65.dp)
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
    CustomNavigationBottom(Modifier,selectedTab = Tab.HOME, {}, {},{})
}