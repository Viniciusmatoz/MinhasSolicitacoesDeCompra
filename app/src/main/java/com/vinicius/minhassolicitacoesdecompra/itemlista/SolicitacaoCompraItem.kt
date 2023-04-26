package com.vinicius.minhassolicitacoesdecompra.itemlista

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vinicius.minhassolicitacoesdecompra.R
import com.vinicius.minhassolicitacoesdecompra.ui.theme.BlueCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowDefault
import com.vinicius.minhassolicitacoesdecompra.ui.theme.YellowText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitacaoCompraItem (){
    Card(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(GreyBox),
        shape = ShapeDefaults.Small,
        elevation = CardDefaults.cardElevation(15.dp),
        modifier = Modifier
            .padding(top = 15.dp, start = 10.dp, end = 10.dp, bottom = 15.dp)
            .fillMaxWidth()
    ){
        Column{
            Row(modifier = Modifier
                .fillMaxWidth(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
                ) {
                Canvas(
                    modifier = Modifier
                        .size(28.dp)
                        .padding(top = 5.dp, start = 8.dp),
                    onDraw = {
                        drawCircle(BlueCircle)
                    })
                Text(
                    text = "SC 005045",
                    color = YellowText,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 10.dp)
                    )
                Text(
                    text = "PC 109179",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 15.dp)
                )
                Text(
                    text = "01/01/2023",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 40.dp)
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth(1f),
            verticalAlignment = Alignment.Top) {
                Text(
                    text = "Compra almoxarifado insumos 2032 UTE Distrito",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 20.dp, bottom = 5.dp, end = 5.dp)
                        .fillMaxWidth(0.70f)
                )
                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 8.dp)
                ){
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                        contentDescription = "icon delete")
                }
                IconButton(onClick = { /*TODO*/ },
                ){
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                        contentDescription = "icon delete")
                }

            }
        }
    }
}
@Preview
@Composable
fun SolicitacaoPreview(){
    SolicitacaoCompraItem()
}