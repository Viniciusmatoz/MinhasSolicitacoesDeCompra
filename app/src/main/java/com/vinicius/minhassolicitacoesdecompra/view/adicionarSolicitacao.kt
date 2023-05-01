package com.vinicius.minhassolicitacoesdecompra.view

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vinicius.minhassolicitacoesdecompra.AppDataBase
import com.vinicius.minhassolicitacoesdecompra.dao.SolicitacaoDao
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.armazemLista
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.calendarioPopUp
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.categoriaLista
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.statusSolicitacaoDeCompra
import com.vinicius.minhassolicitacoesdecompra.model.SolicitacaoDeCompra
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyDefalt
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate


private lateinit var solicitacaoDao: SolicitacaoDao
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdicionarSolicitacao (navController: NavController){

    val listaSolicitacoes: MutableList<SolicitacaoDeCompra> = mutableListOf()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var dataPrevisaoEntrega by remember { mutableStateOf<LocalDate>(LocalDate.now().plusMonths(1)) }
    var numeroSolicitacao by remember { mutableStateOf("")}
    var numeroPedidoCompra by remember { mutableStateOf("") }
    var statusSolicitacao by remember { mutableStateOf("") }
    var descricaoSolicitacao by remember { mutableStateOf("") }
    var armazemSolicitacao by remember { mutableStateOf("") }
    var categoriaSolicitacao by remember { mutableStateOf("") }
    var observacoes by remember { mutableStateOf("") }

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
                              elevation = ButtonDefaults.buttonElevation(10.dp),
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
                              onClick = {
                                  var message = false

                                  scope.launch(Dispatchers.IO){
                                      if (numeroSolicitacao.isEmpty()
                                          || descricaoSolicitacao.isEmpty()
                                          || categoriaSolicitacao.isEmpty()
                                          || statusSolicitacao.isEmpty()
                                          || armazemSolicitacao.isEmpty()){
                                          message = false
                                      }else{

                                          message = true
                                          val solicitacao = SolicitacaoDeCompra(
                                              numeroSolicitacao,
                                              numeroPedidoCompra,
                                              descricaoSolicitacao,
                                              statusSolicitacao,
                                              categoriaSolicitacao,
                                              armazemSolicitacao,
                                              observacoes,
                                              dataPrevisaoEntrega
                                          )
                                          listaSolicitacoes.add(solicitacao)
                                          solicitacaoDao = AppDataBase.getInstance(context).solicitacaoDao()
                                          solicitacaoDao.gravar(listaSolicitacoes)
                                      }
                                  }
                                  scope.launch(Dispatchers.Main){
                                      if (message){
                                          Toast.makeText(context, "Solicitação adicionada", Toast.LENGTH_SHORT).show()
                                          navController.popBackStack()
                                      }else{
                                          Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                                      }
                                  }

                              },
                              elevation = ButtonDefaults.buttonElevation(10.dp),
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
                calendarioPopUp(
                    txtButton = "Previsão de entrega",
                    onDateSelected ={
                        date -> dataPrevisaoEntrega = date
                    }
                )
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
                statusSolicitacaoDeCompra(onStatusSelectedChanged = { newStatus -> statusSolicitacao = newStatus})
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
                armazemLista(onStorageSelectedChanged = {newStorage -> armazemSolicitacao = newStorage})
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp)){
                categoriaLista(onCategorySelectedChanged = {newCategory -> categoriaSolicitacao = newCategory})
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