package com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun armazemLista(){
    val popUpEstado = rememberUseCaseState()
    var armazemSelecionado by remember { mutableStateOf("") }

    val options = listOf(
        ListOption(titleText = "Distrito"),
        ListOption(titleText = "Novo Paraíso"),
        ListOption(titleText = "Monte Cristo"),
        ListOption(titleText = "Outros")
    )

    val selection = ListSelection.Single(
        options = options,
        showRadioButtons = true,
        onSelectOption = { index, option ->
            armazemSelecionado = option.titleText
        }
    )
    Column() {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = ShapeDefaults.Medium,
            onClick = { popUpEstado.show()}) {
            Text(text = "Armazém: ${armazemSelecionado.ifEmpty { "nenhum armazém selecionado" }}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        ListDialog(
            selection = selection,
            state = popUpEstado )
    }
}
