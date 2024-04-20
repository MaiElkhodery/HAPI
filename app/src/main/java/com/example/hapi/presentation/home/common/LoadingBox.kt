package com.example.hapi.presentation.home.common

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hapi.R
import com.example.hapi.presentation.home.loading.LoadingIcon
import com.example.hapi.util.crossFadeAnimation
import kotlinx.coroutines.delay

@Composable
fun LoadingBox(
    isLoading: Boolean = true,
    modifier: Modifier
) {
    var state by remember { mutableStateOf(0) }
    LaunchedEffect(isLoading) {
        while (isLoading) {
            delay(700)
            state = (state + 1) % 4
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Crossfade(
            state, Modifier,
            label = "state",
            animationSpec = crossFadeAnimation(state)
        ) { state ->
            Log.d("Loading", "State: $state")
            when (state) {
                0 -> LoadingIcon(iconId = R.drawable.top_lotus)

                1 -> LoadingIcon(iconId = R.drawable.right_lotus)

                2 -> LoadingIcon(iconId = R.drawable.down_lotus)

                3 -> LoadingIcon(iconId = R.drawable.left_lotus)
            }
        }
    }
}
