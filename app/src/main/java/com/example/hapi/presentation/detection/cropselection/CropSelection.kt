package com.example.hapi.presentation.detection.cropselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
import com.example.hapi.presentation.auth.common.Title
import com.example.hapi.presentation.detection.imageselection.navigateToImageSelection
import com.example.hapi.presentation.home.common.CropCollection
import com.example.hapi.presentation.main.MainViewModel
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.util.Tab

@Composable
fun CropSelection(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(GreenAppColor)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        val (header, text, crops) = createRefs()
        val bottomGuideLine = createGuidelineFromBottom(0.3f)

        NavHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                },
            topText = stringResource(id = R.string.disease),
            downText = stringResource(id = R.string.detection),
            imageId = R.drawable.logo
        ) {
            mainViewModel.setSelectedTab(Tab.HOME)
            navController.popBackStack()
        }


        Title(
            title = stringResource(id = R.string.select_crop),
            modifier = Modifier.constrainAs(text) {
                top.linkTo(header.bottom, margin = 45.dp)
                bottom.linkTo(crops.top, margin = 5.dp)
            }
        )

        CropCollection(
            modifier = Modifier.constrainAs(crops) {
                top.linkTo(text.bottom, margin = 5.dp)
                bottom.linkTo(bottomGuideLine)
            }
        ) { crop ->
            navController.navigateToImageSelection(crop = crop.name)
        }
    }
}

@Preview
@Composable
private fun CropSelectionPreview() {
    CropSelection(navController = rememberNavController())
}