package com.vinicius.minhassolicitacoesdecompra.components




import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedButtonPopUpCustom(
    onOptionSelectedChanged: (String) -> Unit,
    optionsList: List<ListOption>,
    txtTitleOutlinedButton: String,
    modifier: Modifier = Modifier
) {
    val showPopup = remember { mutableStateOf(false) }
    val selectedStatus = remember { mutableStateOf("") }

    Column {
        OutlinedButton(
            modifier = modifier,
            onClick = { showPopup.value = true },
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = txtTitleOutlinedButton
                )
            }
        }

        if (showPopup.value) {
            val popUpState = rememberUseCaseState(visible = true)

            val selection = ListSelection.Single(
                options = optionsList,
                showRadioButtons = true,
                onSelectOption = { index, option ->
                    selectedStatus.value = option.titleText
                    onOptionSelectedChanged(selectedStatus.value)
                    showPopup.value = false
                }
            )

            ListDialog(
                state = popUpState,
                selection = selection
            )
        }
    }
}
