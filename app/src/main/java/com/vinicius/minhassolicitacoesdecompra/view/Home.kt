package com.vinicius.minhassolicitacoesdecompra.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vinicius.minhassolicitacoesdecompra.R
import com.vinicius.minhassolicitacoesdecompra.ui.theme.BlueCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.DarkBackground
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreenCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyText
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyTextBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.RedCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowBasic
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController){
    var search by remember {
        mutableStateOf("")
    }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
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
                colors = CardDefaults.cardColors(GreyBox),
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
                        .padding(top = 8.dp, start = 10.dp, end = 10.dp)
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Canvas(modifier = Modifier
                            .size(55.dp),
                            onDraw = {
                                drawCircle(color = RedCircle)
                            } )
                        Text(
                            text = "SC em andamento",
                            modifier = Modifier
                                .width(70.dp)
                                .padding(top = 2.dp, bottom = 2.dp),
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Normal,
                            fontSize = 14.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column (modifier = Modifier
                        .padding(top = 8.dp, start = 10.dp, end = 10.dp)
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Canvas(modifier = Modifier
                            .size(55.dp),
                            onDraw = {
                                drawCircle(BlueCircle)
                            } )
                        Text(
                            text = "PC em aprovação",
                            modifier = Modifier
                                .width(65.dp)
                                .padding(top = 2.dp, bottom = 2.dp),
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Normal,
                            fontSize = 14.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column (modifier = Modifier
                        .padding(top = 8.dp, start = 10.dp, end = 10.dp)
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Canvas(modifier = Modifier
                            .size(55.dp),
                            onDraw = {
                                drawCircle(color = GreenCircle)
                            } )
                        Text(
                            text = "Aguardando Entrega",
                            modifier = Modifier
                                .width(75.dp)
                                .padding(top = 2.dp, bottom = 2.dp),
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Normal,
                            fontSize = 14.sp,
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
                    .height(55.dp)
                    .padding(start = 15.dp, end = 15.dp),
                singleLine = true,
                label = {
                    Text(text = "Pesquisar")},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White,
                    disabledTextColor = GreyTextBox,
                    containerColor = GreyBox,
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
        }
    }
}
