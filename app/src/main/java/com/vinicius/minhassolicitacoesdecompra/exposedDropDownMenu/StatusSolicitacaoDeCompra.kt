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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun statusSolicitacaoDeCompra(onStatusSelectedChanged: (String) -> Unit){
    val popUpEstado = rememberUseCaseState()
    var statusSelecionado by remember { mutableStateOf("") }

    val options = listOf(
        ListOption(titleText = "SC em andamento"),
        ListOption(titleText = "PC em aprovação"),
        ListOption(titleText = "Aguardando entrega")
    )

    val selection = ListSelection.Single(
        options = options,
        showRadioButtons = true,
        onSelectOption = { index, option ->
            statusSelecionado = option.titleText
            onStatusSelectedChanged(statusSelecionado)
        }
    )
    Column() {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = ShapeDefaults.Medium,
            onClick = { popUpEstado.show()}) {
            Text(text = "Status: ${statusSelecionado.ifEmpty { "nenhum status selecionado" }}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        ListDialog(
            state = popUpEstado,
            selection = selection
        )
    }
}