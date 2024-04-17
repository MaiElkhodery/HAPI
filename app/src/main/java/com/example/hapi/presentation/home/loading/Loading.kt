package com.example.hapi.presentation.home.loading

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.hapi.R
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.crossFadeAnimation
import com.example.hapi.util.text.YellowBlackText
import com.example.hapi.util.text.YellowMediumText
import kotlinx.coroutines.delay

@Composable
fun Loading(
    isLoading: Boolean = true,
) {
    var state by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {

        while (isLoading) {
            delay(720)
            state = (state + 1) % 4
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GreenAppColor)
    ) {

        val (logo, icon, text) = createRefs()
        val topGuideLine = createGuidelineFromTop(0.1f)
        val bottomGuideLine = createGuidelineFromBottom(0.001f)
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(topGuideLine)
                    centerHorizontallyTo(parent)
                }
        )
        Crossfade(
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(logo.bottom, margin = 20.dp)
                    bottom.linkTo(text.top, margin = 20.dp)
                    centerHorizontallyTo(parent)
                },
            targetState = state,
            label = "state",
            animationSpec = crossFadeAnimation(state)
        ) { state ->
            Log.d("Loading", "State: $state")
            when (state) {
                0 -> {
                    LoadingIcon(iconId = R.drawable.top_lotus)
                }

                1 -> {
                    LoadingIcon(iconId = R.drawable.right_lotus)
                }

                2 -> {
                    LoadingIcon(iconId = R.drawable.down_lotus)
                }

                3 -> {
                    LoadingIcon(iconId = R.drawable.left_lotus)
                }
            }
        }
        LoadingText(
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(icon.bottom, margin = 20.dp)
                    bottom.linkTo(bottomGuideLine)
                    centerHorizontallyTo(parent)
                }
        )
    }


}

@Composable
fun LoadingText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        YellowBlackText(size = 24, text = stringResource(id = R.string.please_wait))
        YellowMediumText(size = 24, text = stringResource(id = R.string.processing))
    }
}


@Composable
fun LoadingIcon(
    modifier: Modifier = Modifier,
    iconId: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 35.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(70.dp),
            painter = painterResource(id = iconId),
            contentDescription = "lotus"
        )
    }
}


@Preview
@Composable
fun LoadingPreview() {
    Loading(isLoading = true)
}