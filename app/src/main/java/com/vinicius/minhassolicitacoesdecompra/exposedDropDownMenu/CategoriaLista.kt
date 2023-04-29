package com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyText
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun categoriaLista(){
    val context = LocalContext.current
    val listaCategoria = listOf("Insumos", "Estoque", "Transportes", "Serviço terceirizado", "Outros")
    var isExpanded by remember { mutableStateOf(false) }
    var statusSelecionado by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {isExpanded = it}) {
        OutlinedTextField(
            label = { Text(text = "Categoria") },
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
            listaCategoria.forEach { item ->
                DropdownMenuItem(text = { Text(text = item) }, onClick = {
                    statusSelecionado = item
                    isExpanded = false
                    Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

}