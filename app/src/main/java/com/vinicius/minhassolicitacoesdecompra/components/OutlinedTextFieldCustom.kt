package com.vinicius.minhassolicitacoesdecompra.components


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyTextBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowBasic
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier,
    maxLines: Int = 1,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        cursorColor = YellowDefault,
        focusedBorderColor = YellowDefault,
        unfocusedBorderColor = GreyBox,
        textColor = Color.White,
        disabledTextColor = GreyTextBox,
        focusedLabelColor = YellowBasic,
        unfocusedLabelColor = GreyTextBox
    ),
    readOnly: Boolean = false,
){
    OutlinedTextField(
        value,
        onValueChange,
        label = label,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
        maxLines = maxLines,
        readOnly = readOnly,
        colors = colors,
        shape = ShapeDefaults.Medium
    )
}