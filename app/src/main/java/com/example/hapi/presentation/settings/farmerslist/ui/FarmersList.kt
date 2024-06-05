package com.example.hapi.presentation.settings.farmerslist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.hapi.presentation.common.NavHeader
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.presentation.settings.farmerslist.viewmodel.FarmersListViewModel
import com.example.hapi.presentation.settings.language.LanguageViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Dimens
import com.example.hapi.util.ENGLISH
import com.example.hapi.util.Tab
import com.example.hapi.util.isNetworkConnected

@Composable
fun FarmersListItems(
    farmersViewModel: FarmersListViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel(),
    navController: NavController
) {

    val isEnglish = languageViewModel.appLanguage.collectAsState().value == ENGLISH

    LaunchedEffect(key1 = Unit) {
        if (isNetworkConnected()) {
            farmersViewModel.fetchFarmers()
        } else {
            farmersViewModel.getFarmers()
        }

    }
    val farmers = farmersViewModel.farmersList.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        farmersViewModel.fetchFarmers()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(bottom = 26.dp)
    ) {

        val (navHeader, content) = createRefs()
        val topGuideLine = createGuidelineFromTop(Dimens.top_guideline_settings_options)

        NavHeader(
            modifier = Modifier
                .padding(horizontal = Dimens.small_horizontal_padding)
                .constrainAs(navHeader) {
                    top.linkTo(topGuideLine)
                },
            topText = stringResource(id = R.string.listOf),
            downText = stringResource(id = R.string.farmers),
            imageId = if(isEnglish) R.drawable.settings_back_btn else R.drawable.settings_back_btn_ar,
            imageSize = 80
        ) {
            mainViewModel.setSelectedTab(Tab.SETTINGS)
            navController.popBackStack()
        }

        if (farmers.isEmpty()) {
            NoFarmers(
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(navHeader.bottom, margin = 44.dp)
                }
            )
        } else {
            FarmersGridList(
                farmers = farmers,
                modifier = Modifier.constrainAs(content) {
                    top.linkTo(navHeader.bottom, margin = 44.dp)
                }
            )
        }
    }
}

@Preview
@Composable
fun FarmersListPreview() {
    FarmersListItems(navController = rememberNavController())
}