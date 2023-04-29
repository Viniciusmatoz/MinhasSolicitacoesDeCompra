package com.vinicius.minhassolicitacoesdecompra.view

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.os.Build
import android.util.Log
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.vinicius.minhassolicitacoesdecompra.R
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.armazemLista
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.calendarioPopUp
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.categoriaLista
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.statusSolicitacaoDeCompra
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyDefalt
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdicionarSolicitacao (navController: NavController){

    var numeroSolicitacao by remember {
        mutableStateOf("")
    }
    var descricaoSolicitacao by remember {
        mutableStateOf("")
    }
    var numeroPedidoCompra by remember {
        mutableStateOf("")
    }
    var categoria by remember {
        mutableStateOf("")
    }
    var armazem by remember {
        mutableStateOf("")
    }
    var observacoes by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(bottom = 30.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(GreyBox),
                title = {
                    Text(
                        text = "Adicionar Solicitação de Compra",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                        )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ },
                        ) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Icone voltar", tint = Color.White)
                    }
                }
            )
        },
      bottomBar = {
                  BottomAppBar(
                      containerColor = DarkBackground,
                  ) {
                      Row (modifier = Modifier
                          .fillMaxWidth()
                          .padding(start = 20.dp, end = 20.dp, top = 13.dp, bottom = 13.dp),
                          verticalAlignment = Alignment.Bottom,
                          horizontalArrangement = Arrangement.SpaceAround
                      ){
                          Button(
                              modifier = Modifier
                                  .height(60.dp)
                                  .width(165.dp),
                              onClick = { /*TODO*/ },
                              colors = ButtonDefaults.buttonColors(GreyDefalt),
                          ) {
                              Text(
                                  text = "Cancelar",
                                  color = Color.White,
                                  fontSize = 20.sp )
                          }
                          Spacer(modifier = Modifier.padding(15.dp))
                          Button(
                              modifier = Modifier
                                  .height(60.dp)
                                  .width(165.dp),
                              onClick = { /*TODO*/ },
                          ) {
                              Text(
                                  text = "Adicionar",
                                  fontSize = 20.sp,
                                  fontWeight = FontWeight.Bold
                              )
                          }
                      }
                  }
      },
        containerColor = DarkBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 75.dp)
        ) {

            Row (modifier = Modifier
                .padding(top = 80.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth(1f)){
                calendarioPopUp("Previsão de entrega")
            }

            OutlinedTextField(
                value = numeroSolicitacao ,
                onValueChange = {
                    numeroSolicitacao = it
                },
                label = {
                    Text(text = "Número da Solicitação")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = YellowDefault,
                    focusedBorderColor = YellowDefault,
                    textColor = Color.White,
                    disabledTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp),
                singleLine = true,
                maxLines = 1,
                shape = ShapeDefaults.Medium

                )

            OutlinedTextField(
                value = numeroPedidoCompra ,
                onValueChange = {
                    numeroPedidoCompra = it
                },
                label = {
                    Text(text = "Pedido de Compra")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = YellowDefault,
                    focusedBorderColor = YellowDefault,
                    textColor = Color.White,
                    disabledTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp),
                singleLine = true,
                maxLines = 1,
                shape = ShapeDefaults.Medium
            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp)){
                statusSolicitacaoDeCompra()
            }
            OutlinedTextField(
                value = descricaoSolicitacao ,
                onValueChange = {
                    descricaoSolicitacao = it
                },
                label = {
                    Text(text = "Descrição da requisição")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = YellowDefault,
                    focusedBorderColor = YellowDefault,
                    textColor = Color.White,
                    disabledTextColor = Color.White
                ),
                modifier = Modifier
                    .height(135.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp),
                shape = ShapeDefaults.Medium
            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp)){
                armazemLista()
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp)){
                categoriaLista()
            }
            OutlinedTextField(
                value = observacoes ,
                onValueChange = {
                    observacoes = it
                },
                label = {
                    Text(text = "Observações")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = YellowDefault,
                    focusedBorderColor = YellowDefault,
                    textColor = Color.White,
                    disabledTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp),
                shape = ShapeDefaults.Medium
            )
        }
    }
}