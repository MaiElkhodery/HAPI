package com.example.hapi.presentation.splash

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.signup.landownersignup.selectionstrategy.navigateToCropSelectionStrategy
import com.example.hapi.presentation.main.navigateToMainScreen
import com.example.hapi.presentation.splash.viewmodel.SplashViewModel
import com.example.hapi.presentation.welcome.navigateToWelcomeScreen
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.LANDOWNER
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Composable
fun Splash(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val role = viewModel.role.collectAsState().value
    val token = viewModel.token.collectAsState().value
    val isCropSelected = viewModel.isCropSelected.collectAsState().value

    var state by remember {
        mutableStateOf(1)
    }
    LaunchedEffect(Unit) {
        launch {
            viewModel.getIsCropSelected()
            viewModel.getRole()
            viewModel.getToken()
            while (isActive && state < 5) {
                delay(700)
                state++
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GreenAppColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )
        Crossfade(targetState = state, label = "state") { state ->
            when (state) {
                1 -> {
                    StateOne()
                }

                2 -> {
                    StateTwo()
                }

                3 -> {
                    StateThree()
                }

                4 -> {
                    StateFour()
                }

                else -> {
                    navController.navigateToWelcomeScreen()
                }
            }
        }
    }
    if (state == 5) {
        Log.d("SPLASH", "role: ${role}, token: ${token}, isCropSelected: ${isCropSelected}")
        if (token != null) {
            if (role == LANDOWNER) {
                if (isCropSelected) {
                    Log.d("SPLASH", "Navigate to landowner home")
                    navController.navigateToMainScreen(role)
                } else {
                    Log.d("SPLASH", "Navigate to crop selection")
                    navController.navigateToCropSelectionStrategy()
                }
            } else {
                navController.navigateToMainScreen(role!!)
            }
        } else navController.navigateToWelcomeScreen()
    }
}

@Composable
fun StateOne() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 79.dp)
    ) {}
}

@Composable
fun StateTwo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp),
        horizontalArrangement = Arrangement.Absolute.Left
    ) {
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.left_lotus),
            contentDescription = "lotus"
        )
        Box(modifier = Modifier.weight(2f))
    }
}

@Composable
fun StateThree() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp),
        horizontalArrangement = Arrangement.Absolute.Left
    ) {
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.left_lotus),
            contentDescription = "lotus"
        )
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.top_lotus),
            contentDescription = "lotus"
        )
        Box(modifier = Modifier.weight(1f))
    }
}

@Composable
fun StateFour() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp),
        horizontalArrangement = Arrangement.Absolute.Left
    ) {
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.left_lotus),
            contentDescription = "lotus"
        )
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.top_lotus),
            contentDescription = "lotus"
        )
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.right_lotus),
            contentDescription = "lotus"
        )
    }

}


@Preview
@Composable
fun SplashOnePreview() {
    Splash(rememberNavController())
}