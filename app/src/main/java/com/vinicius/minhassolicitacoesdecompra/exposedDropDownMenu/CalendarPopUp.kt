package com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarioPopUp(
    txtButton: String ,
    onDateSelected: (LocalDate) -> Unit
) {
    val calendarState = rememberUseCaseState()
    var selectedDate by remember { mutableStateOf<LocalDate>(LocalDate.now())}

    Column(
    ) {
        OutlinedButton(
            onClick = { calendarState.show() },
            modifier = Modifier.fillMaxWidth(),
            shape = ShapeDefaults.Medium
        ) {
            Text(
                text = "$txtButton : ${selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        CalendarDialog(
            state = calendarState,
            selection = CalendarSelection.Date { date ->
                selectedDate = date
                onDateSelected (date)
            }
        )
    }
}

