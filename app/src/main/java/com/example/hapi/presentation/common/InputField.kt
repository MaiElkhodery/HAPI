package com.example.hapi.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalLayoutDirection
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapi.R
import com.example.hapi.ui.theme.GreenAppColor
import com.example.hapi.ui.theme.YellowAppColor
import com.example.hapi.util.ScreenSize
import com.example.hapi.util.getScreenWidth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LabeledInputFields(
    width: Dp,
    height: Dp,
    title: String,
    content: StateFlow<String>,
    onChangeContent: (String) -> Unit
) {
    val fontSize = when (getScreenWidth(width)) {
        ScreenSize.SMALL -> 12
        ScreenSize.NORMAL -> 16
        ScreenSize.LARGE -> 18
        ScreenSize.XLARGE -> 20
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        when (title) {
            stringResource(id = R.string.phone_number) -> {
                YellowBlackText(size = fontSize, text = title)
                InputField(
                    width = width,
                    height = height,
                    content = content,
                    imageId = R.drawable.phone
                ) { phoneNumber ->
                    onChangeContent(phoneNumber)
                }
            }

            stringResource(id = R.string.user_name) -> {
                YellowBlackText(size = fontSize, text = title)
                InputField(
                    width = width,
                    height = height,
                    content = content,
                    imageId = R.drawable.user,
                ) { username ->
                    onChangeContent(username)
                }
            }

            stringResource(id = R.string.farm_id) -> {
                YellowBlackText(size = fontSize, text = title)
                InputField(
                    width = width,
                    height = height,
                    content = content,
                    imageId = R.drawable.farm,
                ) { farmId ->
                    onChangeContent(farmId)
                }
            }

            else -> {
                YellowBlackText(size = fontSize, text = title)
                InputField(
                    width = width,
                    height = height,
                    content = content,
                    imageId = R.drawable.password,
                    isPasswordField = true
                ) { password ->
                    onChangeContent(password)
                }
            }
        }
    }

}

@Composable
private fun InputField(
    width: Dp,
    height: Dp,
    content: StateFlow<String>,
    imageId: Int,
    isPasswordField: Boolean = false,
    onChangeText: (String) -> Unit
) {
    val text = content.collectAsState()

    val onFocus = remember {
        mutableStateOf(false)
    }
    var backgroundColor by remember {
        mutableStateOf(YellowAppColor)
    }
    var textColor by remember {
        mutableStateOf(GreenAppColor)
    }
    var passwordVisible by remember { mutableStateOf(false) }

    var trailingIconColor by remember {
        mutableStateOf(GreenAppColor)
    }
    val layoutDirection = LocalLayoutDirection.current

    CompositionLocalProvider(
        LocalLayoutDirection provides layoutDirection,
    ) {
        val fontSize = when {
            width <= 360.dp -> 12
            width in 360.dp..400.dp -> 14
            else -> 16
        }
        val iconSize = when {
            height <= 600.dp -> 24.dp
            height in 600.dp..855.dp -> 28.dp
            else -> 30.dp
        }
        val inputFieldHeight = when {
            height in 600.dp..855.dp -> 48.dp
            else -> 50.dp
        }

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .fillMaxWidth()
                .background(backgroundColor),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (!onFocus.value) {
                Icon(
                    modifier = Modifier
                        .weight(0.11f)
                        .size(iconSize)
                        .padding(start = 12.dp),
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    tint = GreenAppColor
                )

            }

            val visualTransformation = if (isPasswordField) {
                if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            } else VisualTransformation.None


            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .height(inputFieldHeight)
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically)
                    .border(width = 4.dp, color = YellowAppColor)
                    .onFocusChanged {
                        onFocus.value = it.isFocused
                        backgroundColor = if (it.isFocused) GreenAppColor else YellowAppColor
                        textColor = if (it.isFocused) YellowAppColor else GreenAppColor
                        trailingIconColor = if (it.isFocused) YellowAppColor else GreenAppColor
                    },
                maxLines = 1,
                value = text.value,
                onValueChange = {
                    onChangeText(it)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = YellowAppColor,
                    unfocusedBorderColor = YellowAppColor,
                    focusedTextColor = YellowAppColor,
                    unfocusedTextColor = GreenAppColor
                ),
                textStyle = TextStyle(
                    color = textColor,
                    fontFamily = FontFamily(
                        Font(
                            R.font.poppins_black
                        )
                    ),
                    fontSize = fontSize.sp,
                    lineHeight = 10.sp,
                    textAlign = TextAlign.Start
                ),
                visualTransformation = visualTransformation,
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (isPasswordField) KeyboardType.Password
                    else KeyboardType.Text
                ),
                trailingIcon = {
                    if (isPasswordField) {
                        val image =
                            if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = image,
                                contentDescription = null,
                                tint = trailingIconColor
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun LabeledInputFieldPreview() {
    LabeledInputFields(
        width = 300.dp,
        height = 500.dp,
        title = "FARM ID",
        content = MutableStateFlow("100988765"),
    ) {}
}