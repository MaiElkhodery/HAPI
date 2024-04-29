package com.example.hapi.presentation.settings.landowner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hapi.R
import com.example.hapi.presentation.auth.common.NavHeader
import com.example.hapi.presentation.settings.common.LandIdRow
import com.example.hapi.ui.theme.GreenAppColor

@Composable
fun LandownerSettings(
    navController: NavController,
    viewModel: LandownerSettingsViewModel = hiltViewModel()
) {
    val landId = viewModel.landId.collectAsState().value
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
    ) {

        val (welcomeHeader, content, navBottom) = createRefs()
        val topGuideLine = createGuidelineFromTop(.08f)


        NavHeader(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .constrainAs(welcomeHeader) {
                    top.linkTo(topGuideLine)
                    bottom.linkTo(content.top)
                },
            imageId = R.drawable.logo,
            topText = stringResource(id = R.string.your),
            downText = stringResource(id = R.string.settings),
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .constrainAs(content) {
                    top.linkTo(welcomeHeader.bottom, margin = 26.dp)
                }
        ) {
            LandIdRow(landId = landId)
            LandownerSettings(
                modifier = Modifier.padding(top = 11.dp),
                onLanguageClick = { /*TODO*/ },
                onFarmersListClick = { /*TODO*/ },
                onDataAndStorageClick = { /*TODO*/ },
                onHelpAndSupportClick = { /*TODO*/ },
                onAboutUsClick = { /*TODO*/ },
                onDeleteAccountClick = { /*TODO*/ }) {

            }
        }
    }
}

@Preview
@Composable
private fun LandownerSettingsPreview() {
    LandownerSettings(navController = rememberNavController())
}