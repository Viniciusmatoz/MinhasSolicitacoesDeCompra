package com.vinicius.minhassolicitacoesdecompra.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyDisableButton
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault

@Composable
fun ButtonCustom(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textButton: String,
    colorTextButton: Color = DarkBackground,
    colorContainerButton: Color = YellowDefault,
) {
    Button(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorContainerButton,
            disabledContainerColor = GreyDisableButton,
            disabledContentColor = Color.White
        ),
        enabled = enabled
    ) {
        Text(
            text = textButton,
            color = colorTextButton,
            fontSize = 21.sp
        )
    }
}