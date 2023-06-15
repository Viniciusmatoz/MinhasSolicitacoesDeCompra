package com.vinicius.minhassolicitacoesdecompra.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vinicius.minhassolicitacoesdecompra.AppDataBase
import com.vinicius.minhassolicitacoesdecompra.R
import com.vinicius.minhassolicitacoesdecompra.dao.SolicitacaoDao
import com.vinicius.minhassolicitacoesdecompra.itemlista.SolicitacaoCompraItem
import com.vinicius.minhassolicitacoesdecompra.model.SolicitacaoDeCompra
import com.vinicius.minhassolicitacoesdecompra.ui.theme.BlueCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreenCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyCardBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyTextBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.RedCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowBasic
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var solicitacaoDao: SolicitacaoDao

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController){

    val scope = rememberCoroutineScope()
    val listaSolicitacoes:MutableList<SolicitacaoDeCompra> = mutableListOf()
    val context = LocalContext.current
    solicitacaoDao = AppDataBase.getInstance(context).solicitacaoDao()
    scope.launch(Dispatchers.IO){
        val solicitacoes = solicitacaoDao.getSolicitacoes()

        for (sc in solicitacoes){
            listaSolicitacoes.add(sc)
        }
    }

    var search by remember { mutableStateOf("") }

    val listaFiltrada = remember {
        mutableStateListOf<SolicitacaoDeCompra>()
    }

    LaunchedEffect(search) {
        val searchLowerCase = search.lowercase()
        listaFiltrada.clear()
        listaFiltrada.addAll(listaSolicitacoes.filter { solicitacao ->
            solicitacao.numeroSolicitacao.contains(searchLowerCase) ||
                    solicitacao.numeroPedido.contains(searchLowerCase) ||
                    solicitacao.descricao.contains(searchLowerCase) ||
                    solicitacao.statusSolicitacao.contains(searchLowerCase) ||
                    solicitacao.armazemDestino.contains(searchLowerCase) ||
                    solicitacao.categoriaSolicitacao.contains(searchLowerCase) ||
                    solicitacao.observacoesSolicitacao.contains(searchLowerCase)
        })
    }

    Scaffold (
        modifier = Modifier.fillMaxSize().fillMaxHeight(),
        containerColor = DarkBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("adicionarSolicitacao") },
                shape = ShapeDefaults.ExtraLarge,
                containerColor = YellowBasic,
                elevation = FloatingActionButtonDefaults.elevation(10.dp),
                modifier = Modifier.padding(bottom = 5.dp)
            ) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_add ), contentDescription ="Icon add" )
            }
        }
    ){
        Column{
            Card(
                modifier = Modifier
                    .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .height(150.dp),
                shape = ShapeDefaults.Medium,
                colors = CardDefaults.cardColors(GreyCardBox),
                elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    text = "Solicitações de compra",
                    color = YellowDefault,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Row (
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Column (modifier = Modifier
                        .padding(top = 8.dp, start = 10.dp, end = 7.dp)
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Box(modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = RedCircle,
                                shape = RoundedCornerShape(50)),
                        contentAlignment = Alignment.Center){
                            val countText = listaSolicitacoes.count { it.statusSolicitacao == "SC em andamento" }
                            Text(
                                text = countText.toString(),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        Text(
                            text = "SC em andamento",
                            modifier = Modifier
                                .padding(top = 2.dp, bottom = 2.dp)
                                .width(74.dp),
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Normal,
                            fontSize = 13.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column (modifier = Modifier
                        .padding(top = 8.dp, end = 7.dp)
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

                        Box(modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = BlueCircle,
                                shape = RoundedCornerShape(50)),
                            contentAlignment = Alignment.Center){
                            val countText = listaSolicitacoes.count { it.statusSolicitacao == "PC em aprovação" }
                            Text(
                                text = countText.toString(),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                            )
                        }
                        Text(
                            text = "PC em aprovação",
                            modifier = Modifier
                                .width(65.dp)
                                .padding(top = 2.dp, bottom = 2.dp),
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Normal,
                            fontSize = 13.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column (modifier = Modifier
                        .padding(top = 8.dp, end = 10.dp)
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Box(modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = GreenCircle,
                                shape = RoundedCornerShape(50)),
                            contentAlignment = Alignment.Center){
                            val countText = listaSolicitacoes.count { it.statusSolicitacao == "Aguardando entrega" }
                            Text(
                                text = countText.toString(),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }
                        Text(
                            text = "Aguardando Entrega",
                            modifier = Modifier
                                .width(75.dp)
                                .padding(top = 2.dp, bottom = 2.dp),
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Normal,
                            fontSize = 13.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            OutlinedTextField(
                value = search,
                onValueChange ={search = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 15.dp, end = 15.dp),
                singleLine = true,
                label = {
                    Text(text = "Pesquisar")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    disabledTextColor = GreyTextBox,
                    containerColor = GreyCardBox,
                    unfocusedLabelColor = GreyTextBox,
                    focusedLabelColor = YellowDefault,
                    focusedBorderColor = YellowDefault),
                leadingIcon ={
                    Icon(imageVector = Icons.Rounded.Search,
                        contentDescription ="search icon" )
                },
                trailingIcon ={
                    IconButton(onClick = {})
                    {
                        Image(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Icon filter",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
            LazyColumn(modifier = Modifier.fillMaxWidth().fillMaxSize()) {
                itemsIndexed(listaFiltrada) { position, item ->
                    SolicitacaoCompraItem(navController, position, listaFiltrada, context)
                }
            }

        }
    }
}
