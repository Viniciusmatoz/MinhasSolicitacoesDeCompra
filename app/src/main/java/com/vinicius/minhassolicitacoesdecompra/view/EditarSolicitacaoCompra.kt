package com.vinicius.minhassolicitacoesdecompra.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.vinicius.minhassolicitacoesdecompra.components.OutlinedButtonCustom
import com.vinicius.minhassolicitacoesdecompra.components.OutlinedButtonPopUpCustom
import com.vinicius.minhassolicitacoesdecompra.components.OutlinedTextFieldCustom
import com.vinicius.minhassolicitacoesdecompra.model.SolicitacaoDeCompra
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyCardBox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarSolicitacaoCompra(navController: NavController, numeroSolicitacao: String) {


    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dao = AppDataBase.getInstance(context).solicitacaoDao()

    val solicitacaoState = remember { mutableStateOf<SolicitacaoDeCompra?>(null) }
    val errorState = remember { mutableStateOf<String?>(null) }

    // Variáveis para armazenar os valores editáveis
    var novoPedidoCompra by remember { mutableStateOf("") }
    var novaDataCriacao by remember { mutableStateOf("") }
    var novaDataPrevisaoEntrega by remember { mutableStateOf("") }
    var novaDescricao by remember { mutableStateOf("") }
    var novoArmazem by remember { mutableStateOf("") }
    var novaCategoria by remember { mutableStateOf("") }
    var novaObservacao by remember { mutableStateOf("") }
    var novoStatusSolicitacao by remember { mutableStateOf("") }


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
                }
            )
        },
        containerColor = DarkBackground,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
        ) {
            if (errorState.value != null) {
                Text(errorState.value!!)
            } else {
                val solicitacao = solicitacaoState.value
                if (solicitacao != null) {

                    OutlinedTextFieldCustom(
                        value = solicitacao.numeroSolicitacao,
                        onValueChange = {},
                        label = { Text(text = "Numero Solicitação") },
                        keyboardOptions = KeyboardOptions.Default,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        readOnly = true
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
                            ListOption(titleText = "Aguardando Entrega"),
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
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        maxLines = Int.MAX_VALUE
                    )

                    Text("Descrição da solicitação: ${solicitacao.descricao}")
                    Text("Data de criação: ${solicitacao.dataCriacao}")
                }
            }
        }
    }
}

