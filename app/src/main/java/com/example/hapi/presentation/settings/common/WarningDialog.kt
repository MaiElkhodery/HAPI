package com.example.hapi.presentation.settings.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hapi.R
import com.example.hapi.presentation.common.DarkGreenBoldText
import com.example.hapi.presentation.common.RedBlackText
import com.example.hapi.presentation.common.YellowBlackText
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.WarningColor
import com.example.hapi.ui.theme.YellowAppColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WarningDialogWithPassword(
    modifier: Modifier = Modifier,
    withPassword: Boolean = true,
    warningText: String,
    additionalWarningText: String,
    password: StateFlow<String>,
    onClickConfirm: () -> Unit,
    onChangePassword: (String) -> Unit,
    onClickCancel: () -> Unit
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(YellowAppColor),
            verticalArrangement = Arrangement.Center
        ) {
            CancelButton {
                onClickCancel()
            }
            WarningRow(
                text = warningText
            )
            if (additionalWarningText.isNotBlank()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.warning),
                        contentDescription = null,
                        tint = DarkGreenAppColor,
                        modifier = Modifier
                            .size(26.dp)
                            .padding(end = 8.dp)
                    )

                    DarkGreenBoldText(size = 12, text = additionalWarningText)
                }
            }
            if (withPassword) {
                ConfirmPassword(content = password) { password ->
                    onChangePassword(password)
                }
            }
            ConfirmButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 24.dp)
            ) {
                onClickConfirm()
            }
        }
    }
}

@Composable
fun ConfirmButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(WarningColor)
            .padding(horizontal = 24.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        YellowBlackText(size = 20, text = stringResource(id = R.string.confirm))
    }
}

@Composable
private fun WarningRow(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(start = 40.dp, end = 40.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.warnning),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical=14.dp, horizontal = 5.dp)
                .weight(.4f)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            DarkGreenBoldText(
                size = 16,
                text = stringResource(id = R.string.warning)
            )
            RedBlackText(
                size = 16,
                text = text
            )
        }

    }
}

@Composable
fun CancelButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp, horizontal = 18.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painterResource(id = R.drawable.cancel_btn),
            contentDescription = null,
            tint = DarkGreenAppColor,
            modifier = modifier
                .size(26.dp)
                .fillMaxHeight()
                .clickable {
                    onClick()
                }
        )
    }
}

@Composable
private fun ConfirmPassword(
    content: StateFlow<String>,
    onChangeText: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, top = 18.dp),
        verticalArrangement = Arrangement.Top
    ) {
        val text = content.collectAsState()
        var passwordVisible by remember { mutableStateOf(false) }

        DarkGreenBoldText(
            size = 12,
            text = stringResource(id = R.string.confirm_password)
        )

        val visualTransformation =
            if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .border(width = 2.dp, color = DarkGreenAppColor)
                .clip(RoundedCornerShape(7.dp)),
            maxLines = 1,
            value = text.value,
            onValueChange = {
                onChangeText(it)
            },
            textStyle = TextStyle(
                color = DarkGreenAppColor,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_bold
                    )
                ),
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                lineHeight = 10.sp
            ),
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                val image =
                    if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = null,
                        tint = DarkGreenAppColor
                    )
                }

            }
        )
    }

}

@Preview
@Composable
private fun WarningDialogWithPasswordPreview() {

    WarningDialogWithPassword(
        warningText = stringResource(id = R.string.delete_account_warning),
        password = MutableStateFlow("098765"),
        additionalWarningText = "This action cannot be undone.",
        onClickConfirm = {},
        onChangePassword = {},
        onClickCancel = {}
    )

}
