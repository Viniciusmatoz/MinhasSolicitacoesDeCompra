package com.vinicius.minhassolicitacoesdecompra.view

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.maxkeppeler.sheets.list.models.ListOption
import com.vinicius.minhassolicitacoesdecompra.AppDataBase
import com.vinicius.minhassolicitacoesdecompra.components.ButtonCustom
import com.vinicius.minhassolicitacoesdecompra.components.OutlinedButtonPopUpCustom
import com.vinicius.minhassolicitacoesdecompra.components.OutlinedTextFieldCustom
import com.vinicius.minhassolicitacoesdecompra.dao.SolicitacaoDao
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.CalendarioPopUp
import com.vinicius.minhassolicitacoesdecompra.model.SolicitacaoDeCompra
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyCardBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyDefalt
import com.vinicius.minhassolicitacoesdecompra.ui.theme.RedCircle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate


private lateinit var solicitacaoDao: SolicitacaoDao
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdicionarSolicitacao (navController: NavController){

    val listaSolicitacoes: MutableList<SolicitacaoDeCompra> = mutableListOf()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var dataCriacao by remember { mutableStateOf<LocalDate>(LocalDate.now())}
    var dataPrevisaoEntrega by remember { mutableStateOf<LocalDate>(LocalDate.now().plusMonths(1)) }
    var numeroSolicitacao by remember { mutableStateOf("")}
    var numeroPedidoCompra by remember { mutableStateOf("") }
    var statusSolicitacao by remember { mutableStateOf("") }
    var descricaoSolicitacao by remember { mutableStateOf("") }
    var armazemSolicitacao by remember { mutableStateOf("") }
    var categoriaSolicitacao by remember { mutableStateOf("") }
    var observacoes by remember { mutableStateOf("") }
    var solicitacaoExistente by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(bottom = 30.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(GreyCardBox),
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
                        onClick = { navController.popBackStack()},
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
                          .padding(top = 13.dp, bottom = 13.dp),
                          verticalAlignment = Alignment.Bottom,
                          horizontalArrangement = Arrangement.SpaceAround
                      ){
                          Box(modifier = Modifier
                              .weight(1f)
                              .padding(horizontal = 4.dp)) {
                              ButtonCustom(
                                  modifier = Modifier.fillMaxWidth(),
                                  onClick = { navController.popBackStack() },
                                  textButton = "Cancelar",
                                  colorContainerButton = GreyDefalt,
                                  colorTextButton = Color.White
                              )
                          }
                          Box(modifier = Modifier
                              .weight(1f)
                              .padding(horizontal = 4.dp)) {
                              ButtonCustom(
                                  modifier = Modifier.fillMaxWidth(),
                                  textButton = "Adicionar",
                                  enabled = !solicitacaoExistente,
                                  onClick = {
                                      var message = false
                                      scope.launch(Dispatchers.IO) {
                                          if (numeroSolicitacao.isEmpty()
                                              || descricaoSolicitacao.isEmpty()
                                              || categoriaSolicitacao.isEmpty()
                                              || statusSolicitacao.isEmpty()
                                              || armazemSolicitacao.isEmpty()
                                          ) {
                                              message = false
                                          } else {
                                              message = true
                                              val solicitacao = SolicitacaoDeCompra(
                                                  numeroSolicitacao,
                                                  numeroPedidoCompra,
                                                  descricaoSolicitacao,
                                                  statusSolicitacao,
                                                  categoriaSolicitacao,
                                                  armazemSolicitacao,
                                                  observacoes,
                                                  dataCriacao,
                                                  dataPrevisaoEntrega
                                              )
                                              solicitacaoDao =
                                                  AppDataBase.getInstance(context).solicitacaoDao()
                                              listaSolicitacoes.add(solicitacao)
                                              solicitacaoDao.gravar(listaSolicitacoes)
                                          }
                                      }
                                      scope.launch(Dispatchers.Main) {
                                          if (message) {
                                              Toast.makeText(
                                                  context,
                                                  "Solicitação adicionada",
                                                  Toast.LENGTH_SHORT
                                              ).show()
                                              navController.popBackStack()
                                          } else {
                                              Toast.makeText(
                                                  context,
                                                  "Preencha todos os campos",
                                                  Toast.LENGTH_SHORT
                                              ).show()
                                          }
                                      }

                                  },
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
                .padding(top = 80.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)){
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)) {
                    CalendarioPopUp(
                        txtButton = "Data criação",
                        onDateSelected = { date ->
                            dataCriacao = date
                        },
                        initialDate = LocalDate.now()
                    )
                }
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)) {
                    CalendarioPopUp(
                        txtButton = "Data previsão entrega",
                        onDateSelected = { date ->
                            dataPrevisaoEntrega = date
                        },
                        initialDate = LocalDate.now()
                    )
                }

            }


            OutlinedTextFieldCustom(
                value = numeroSolicitacao,
                onValueChange = { newNumber ->
                    numeroSolicitacao = newNumber
                    if (newNumber.isNotEmpty()) {
                        scope.launch(Dispatchers.IO) {
                            solicitacaoDao = AppDataBase.getInstance(context).solicitacaoDao()
                            val existingSolicitacao = solicitacaoDao.getById(newNumber)
                            if (existingSolicitacao != null) {
                                withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "Já existe uma solicitação com este número", Toast.LENGTH_SHORT).show()
                                    solicitacaoExistente = true
                                }
                            }else{
                                solicitacaoExistente = false
                            }
                        }
                    }
                },
                label = { Text(text = "Número da Solicitação") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = if (solicitacaoExistente) {
                        RedCircle
                    } else {
                        Color.White
                    },
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp),
            )

            OutlinedTextFieldCustom(
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp),
            )

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp)){

                OutlinedButtonPopUpCustom(
                    onOptionSelectedChanged = { novoStatus ->
                        statusSolicitacao = novoStatus
                    },
                    optionsList = listOf(
                        ListOption(titleText = "SC em andamento"),
                        ListOption(titleText = "PC em aprovação"),
                        ListOption(titleText = "Aguardando entrega"),
                        ListOption(titleText = "Outros")
                    ),
                    txtTitleOutlinedButton = "Status: ${statusSolicitacao.ifEmpty { "selecione uma opção" }}"
                )
                
            }
            OutlinedTextFieldCustom(
                value = descricaoSolicitacao ,
                onValueChange = {
                    descricaoSolicitacao = it
                },
                label = {
                    Text(text = "Descrição da requisição")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .height(135.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp),
                maxLines = Int.MAX_VALUE
            )

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp)){
                
                OutlinedButtonPopUpCustom(
                    onOptionSelectedChanged ={ newStorage -> armazemSolicitacao = newStorage},
                    optionsList = listOf(
                        ListOption(titleText = "Distrito"),
                        ListOption(titleText = "Novo Paraíso"),
                        ListOption(titleText = "Monte Cristo"),
                        ListOption(titleText = "Outros"),
                    ),
                    txtTitleOutlinedButton = "Armazém: ${armazemSolicitacao.ifEmpty { "selecione uma opção" }}"
                )
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp)){

                OutlinedButtonPopUpCustom(
                    onOptionSelectedChanged ={ newCategory -> categoriaSolicitacao = newCategory},
                    optionsList = listOf(
                        ListOption(titleText = "Estoque"),
                        ListOption(titleText = "Insumos"),
                        ListOption(titleText = "Serviço terceiros"),
                        ListOption(titleText = "Transportes"),
                        ListOption(titleText = "Outros"),
                    ),
                    txtTitleOutlinedButton = "Categoria: ${categoriaSolicitacao.ifEmpty { "selecione uma opção" }}"
                )
            }
            OutlinedTextFieldCustom(
                value = observacoes ,
                onValueChange = {
                    observacoes = it
                },
                label = {
                    Text(text = "Observações (opcional)")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 0.dp, end = 10.dp, bottom = 10.dp),
                maxLines = Int.MAX_VALUE
            )
        }
    }
}