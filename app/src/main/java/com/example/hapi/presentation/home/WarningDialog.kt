package com.example.hapi.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.hapi.ui.theme.DarkGreenAppColor
import com.example.hapi.ui.theme.WarningColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.DarkGreenBoldText
import com.example.hapi.util.RedBlackText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WarningDialog(
    modifier: Modifier = Modifier,
    warningText: String,
    warningIconSize: Int,
    password: StateFlow<String>,
    onChangePassword: (String) -> Unit
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = YellowAppColor
            )
        ) {
            CancelButton {

            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                WarningRow(
                    text = warningText,
                    iconSize = warningIconSize
                )
            }
            ConfirmPassword(content = password) { password ->
                onChangePassword(password)
            }

        }
    }
}

@Composable
private fun WarningRow(
    modifier: Modifier = Modifier,
    text: String,
    iconSize: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box (
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = R.drawable.warnning),
                contentDescription = null,
                tint = WarningColor,
                modifier = Modifier
                    .size(iconSize.dp)
                    .fillMaxHeight()
            )
        }
        
        Column {
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
private fun CancelButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = YellowAppColor,
            modifier = Modifier
                .size(16.dp)
                .fillMaxHeight()
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
            .padding(8.dp),
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
                .border(width = 2.dp, color = DarkGreenAppColor),
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
private fun WarningDialogPreview() {
    WarningDialog(
        warningText = stringResource(id = R.string.delete_account_warning),
        warningIconSize = 44,
        password = MutableStateFlow("098765"),
    ) {}
}