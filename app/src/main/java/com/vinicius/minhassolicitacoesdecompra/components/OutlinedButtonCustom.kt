package com.vinicius.minhassolicitacoesdecompra.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowBasic

@Composable
fun OutlinedButtonCustom(
    modifier: Modifier,
    onClick: () -> Unit,
    txtContentButton: String

){
    OutlinedButton(
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkBackground,
            contentColor = YellowBasic,
            disabledContainerColor = DarkBackground,
            disabledContentColor = YellowBasic
        ),
        modifier = modifier,
        shape = ShapeDefaults.Medium,
        onClick = {

        }
    ){
        Text(text = txtContentButton,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}