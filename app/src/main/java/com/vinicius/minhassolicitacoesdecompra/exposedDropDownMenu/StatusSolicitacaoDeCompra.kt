package com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun statusSolicitacaoDeCompra(){
    val context = LocalContext.current
    val listaStatusSolicitacao = listOf("SC em andamento", "PC em aprovação", "Aguardando Entrega")
    var isExpanded by remember { mutableStateOf(false) }
    var statusSelecionado by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {isExpanded = it}) {
        OutlinedTextField(
            label = { Text(text = "Status")},
            value = statusSelecionado,
            onValueChange = {},
        readOnly = true,
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
        },
            shape = ShapeDefaults.Medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = YellowDefault,
                focusedBorderColor = YellowDefault,
                textColor = Color.White,
                disabledTextColor = Color.White,
                unfocusedLabelColor = GreyText,
            ),
        modifier = Modifier
            .menuAnchor()
            .fillMaxWidth(1f)
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
         listaStatusSolicitacao.forEach { item ->
             DropdownMenuItem(text = {Text(text = item)}, onClick = {
                 statusSelecionado = item
                 isExpanded = false
                 Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
             })
         }
        }
    }

}