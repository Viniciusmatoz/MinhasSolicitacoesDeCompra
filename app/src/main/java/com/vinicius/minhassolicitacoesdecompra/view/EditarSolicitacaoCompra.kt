package com.vinicius.minhassolicitacoesdecompra.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.vinicius.minhassolicitacoesdecompra.exposedDropDownMenu.CalendarioPopUp
import com.vinicius.minhassolicitacoesdecompra.model.SolicitacaoDeCompra
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyCardBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyDefalt
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyTextBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarSolicitacaoCompra(navController: NavController, numeroSolicitacao: String) {


    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dao = AppDataBase.getInstance(context).solicitacaoDao()
    val isMenuOpen = remember{ mutableStateOf(false)}
    val solicitacaoState = remember { mutableStateOf<SolicitacaoDeCompra?>(null) }
    val errorState = remember { mutableStateOf<String?>(null) }
    var buttonEnabled = true

    // Variáveis para armazenar os valores editáveis
    var novoPedidoCompra by rememberSaveable { mutableStateOf("") }
    var novaDataCriacao by rememberSaveable { mutableStateOf<LocalDate>(LocalDate.now())}
    var novaDataPrevisaoEntrega by rememberSaveable { mutableStateOf<LocalDate>(LocalDate.now())}
    var novaDescricao by rememberSaveable { mutableStateOf("") }
    var novoArmazem by rememberSaveable { mutableStateOf("") }
    var novaCategoria by rememberSaveable { mutableStateOf("") }
    var novaObservacao by rememberSaveable { mutableStateOf("") }
    var novoStatusSolicitacao by rememberSaveable { mutableStateOf("") }


    LaunchedEffect(key1 = numeroSolicitacao) {
        try {
            val solicitacao = withContext(Dispatchers.IO) {
                dao.getById(numeroSolicitacao)
            }
            solicitacaoState.value = solicitacao

            // Inicializar os valores editáveis com os valores existentes

            novoPedidoCompra = solicitacao?.numeroPedido ?: ""
            novoStatusSolicitacao = solicitacao?.statusSolicitacao ?: ""
            novoArmazem = solicitacao?.armazemDestino ?: ""
            novaObservacao = solicitacao?.observacoesSolicitacao ?: ""
            novaDescricao = solicitacao?.descricao ?: ""
            novaCategoria = solicitacao?.categoriaSolicitacao ?: ""
            novaDataCriacao = solicitacao?.dataCriacao ?: novaDataCriacao
            novaDataPrevisaoEntrega = solicitacao?.dataPrevisaoEntrega ?: novaDataPrevisaoEntrega

        } catch (e: Exception) {
            errorState.value = "Erro ao recuperar a solicitação: ${e.message}"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(bottom = 30.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(GreyCardBox),
                title = {
                    Text(
                        text = "Editar Solicitação de Compra",
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
                },
                actions = {
                    IconButton(onClick = {isMenuOpen.value = true })
                    {
                        Icon(Icons.Rounded.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = isMenuOpen.value,
                        onDismissRequest = { isMenuOpen.value = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Finalizar Solicitação") },
                            onClick = {
                                    val alertDialog = AlertDialog.Builder(context)
                                    alertDialog
                                        .setTitle("Finalizar Solicitação")
                                        .setMessage("Deseja Finalizar esta Solicitação?")
                                    alertDialog.setPositiveButton("Sim"){_,_->
                                        scope.launch(Dispatchers.IO){
                                            dao.deleteSolicitacao(numeroSolicitacao)
                                        }
                                        scope.launch(Dispatchers.Main){
                                            navController.popBackStack()
                                            Toast.makeText(context,"Solicitação finalizada com sucesso",Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    alertDialog.setNegativeButton("Cancelar"){_,_->}
                                    alertDialog.show()
                            }
                        )
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
                            enabled = buttonEnabled,
                            textButton = "Salvar alterações",
                            onClick = {
                                var message = false
                                scope.launch(Dispatchers.IO) {
                                    if (numeroSolicitacao.isEmpty()
                                        || novaDescricao.isEmpty()
                                        || novaCategoria.isEmpty()
                                        || novoStatusSolicitacao.isEmpty()
                                        || novoArmazem.isEmpty()
                                    ) {
                                        message = false
                                        buttonEnabled = false
                                    } else {
                                        message = true
                                        scope.launch(Dispatchers.IO){
                                            dao.updateSolicitacao(
                                                numeroSolicitacao,
                                                novoPedidoCompra,
                                                novaDescricao,
                                                novoStatusSolicitacao,
                                                novaCategoria,
                                                novoArmazem,
                                                novaObservacao,
                                                novaDataCriacao,
                                                novaDataPrevisaoEntrega)
                                        }
                                    }
                                }
                                scope.launch(Dispatchers.Main) {
                                    if (message) {
                                        Toast.makeText(
                                            context,
                                            "Solicitação alterada",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        navController.popBackStack()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Preencha todos os campos necessários",
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
        containerColor = DarkBackground,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (errorState.value != null) {
                Text(errorState.value!!)
            } else {
                val solicitacao = solicitacaoState.value
                if (solicitacao != null) {

                    Row (modifier = Modifier
                        .padding(top = 20.dp)){
                        Box(modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)) {
                            CalendarioPopUp(
                                txtButton = "Data criação",
                                onDateSelected = { date ->
                                    novaDataCriacao = date
                                },
                                initialDate = novaDataCriacao
                            )
                        }
                        Box(modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)) {
                            CalendarioPopUp(
                                txtButton = "Data previsão entrega",
                                onDateSelected = { date ->
                                    novaDataPrevisaoEntrega = date
                                },
                                initialDate = novaDataPrevisaoEntrega
                            )
                        }

                    }

                    OutlinedTextFieldCustom(
                        value = solicitacao.numeroSolicitacao,
                        onValueChange = {},
                        label = { Text(text = "Numero Solicitação") },
                        keyboardOptions = KeyboardOptions.Default,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        readOnly = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = GreyTextBox,
                            disabledLabelColor = GreyTextBox,
                            focusedLabelColor = YellowDefault,
                            disabledTextColor = GreyTextBox,
                            unfocusedLabelColor = GreyTextBox,
                            focusedBorderColor = YellowDefault,
                        )
                    )

                    OutlinedTextFieldCustom(
                        value = novoPedidoCompra,
                        onValueChange = {novoPedidoCompra = it},
                        label = { Text(text = "Numero Pedido de Compra") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        readOnly = false
                    )

                    OutlinedButtonPopUpCustom(
                        modifier = Modifier.padding(top = 10.dp),
                        onOptionSelectedChanged = { novoStatus ->
                            novoStatusSolicitacao = novoStatus
                        },
                        optionsList = listOf(
                            ListOption(titleText = "SC em andamento"),
                            ListOption(titleText = "PC em aprovação"),
                            ListOption(titleText = "Aguardando entrega"),
                            ListOption(titleText = "Outros")
                        ),
                        txtTitleOutlinedButton = "Status: $novoStatusSolicitacao"
                    )

                    OutlinedTextFieldCustom(
                        value = novaDescricao ,
                        onValueChange = {
                            novaDescricao = it
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
                            .padding(top = 10.dp),
                        maxLines = Int.MAX_VALUE
                    )

                    OutlinedButtonPopUpCustom(
                        modifier = Modifier.padding(top = 10.dp),
                        onOptionSelectedChanged ={ newStorage -> novoArmazem = newStorage},
                        optionsList = listOf(
                            ListOption(titleText = "Distrito"),
                            ListOption(titleText = "Novo Paraíso"),
                            ListOption(titleText = "Monte Cristo"),
                            ListOption(titleText = "Outros"),
                        ),
                        txtTitleOutlinedButton = "Armazém: ${novoArmazem.ifEmpty { "selecione uma opção" }}"
                    )

                    OutlinedButtonPopUpCustom(
                        modifier = Modifier.padding(top = 10.dp),
                        onOptionSelectedChanged ={ newCategory -> novaCategoria = newCategory},
                        optionsList = listOf(
                            ListOption(titleText = "Estoque"),
                            ListOption(titleText = "Insumos"),
                            ListOption(titleText = "Serviço terceiros"),
                            ListOption(titleText = "Transportes"),
                            ListOption(titleText = "Outros"),
                        ),
                        txtTitleOutlinedButton = "Categoria: ${novaCategoria.ifEmpty { "selecione uma opção" }}"
                    )

                    OutlinedTextFieldCustom(
                        value = novaObservacao ,
                        onValueChange = {
                            novaObservacao = it
                        },
                        label = {
                            Text(text = "Observações (opcional)")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier
                            .height(190.dp)
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 70.dp),
                        maxLines = Int.MAX_VALUE
                    )
                }
            }
        }
    }
}

