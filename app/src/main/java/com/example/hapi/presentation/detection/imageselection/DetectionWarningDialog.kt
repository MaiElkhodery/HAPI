package com.example.hapi.presentation.detection.imageselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hapi.R
import com.example.hapi.presentation.settings.CancelButton
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.presentation.common.DarkGreenExtraBoldText

@Composable
fun DetectionWarningDialog(
    modifier: Modifier = Modifier,
    topWarningId: Int,
    downWarningId: Int,
    onClickCancel: () -> Unit
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(YellowAppColor)
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CancelButton (
                modifier = Modifier.size(18.dp)
            ){
                onClickCancel()
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.warning),
                    contentDescription = null,
                    tint = DarkGreenAppColor
                )
            }

            DarkGreenExtraBoldText(
                size = 15,
                text = stringResource(id = topWarningId)
            )

            Text(
                modifier = modifier,
                color = DarkGreenAppColor,
                fontSize = 10.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_semibold
                    )
                ),
                text = stringResource(id = downWarningId)
            )
        }
    }
}

@Preview
@Composable
fun DetectionWarningDialogPreview() {
    DetectionWarningDialog(
        topWarningId = R.string.something_wrong,
        downWarningId = R.string.another_img,
        onClickCancel = {}
    )
}