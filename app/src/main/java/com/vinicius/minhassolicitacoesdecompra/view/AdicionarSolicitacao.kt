package com.vinicius.minhassolicitacoesdecompra.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.vinicius.minhassolicitacoesdecompra.R
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdicionarSolicitacao (navController: NavController){

    val statusSolicitacao = listOf("SC em andamento", "PC em aprovação", "Aguardando entrega")

    var estadoStatusSolicitacao by remember {
        mutableStateOf(0)
    }

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
        containerColor = DarkBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
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
                    .padding(start = 10.dp, top = 80.dp, end = 10.dp, bottom = 15.dp),
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
            Row() {
                statusSolicitacao.forEachIndexed { i, opcao: String ->
                    Row(
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = SpaceAround,

                        ) {
                        RadioButton(
                            selected = estadoStatusSolicitacao == i,
                            onClick = { estadoStatusSolicitacao = i })
                    }
                }
            }
        }
    }
}



@Composable
@Preview
private fun AdicionarSolicitacao(){
    AdicionarSolicitacao(navController = rememberNavController())
}