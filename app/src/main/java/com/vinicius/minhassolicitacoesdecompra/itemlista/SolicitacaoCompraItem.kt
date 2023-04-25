package com.vinicius.minhassolicitacoesdecompra.itemlista

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
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
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(top = 12.dp, start = 10.dp, bottom = 0.dp, end = 0.dp).fillMaxWidth()
        ) {
            val (txtSolicitacao,txtPedido,txtDescricao,txtData,imgStatus,btnFavoritar,btnDeletar) = createRefs()
            Canvas(
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(imgStatus) {
                        top.linkTo(parent.top, margin = 4.dp)
                        start.linkTo(parent.start)
                    },
                onDraw = {drawCircle(BlueCircle)})

            Text(
                text = "SC 005045",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .constrainAs(txtSolicitacao){
                        top.linkTo(parent.top)
                        start.linkTo(imgStatus.end, margin = 15.dp)
                    }
            )
            Text(
                text = "PC 109179",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .constrainAs(txtPedido){
                        top.linkTo(parent.top)
                        start.linkTo(txtSolicitacao.end, margin = 22.dp)
                    }
            )
            Text(
                text = "01/01/2023",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .constrainAs(txtData){
                        top.linkTo(parent.top, margin = 3.dp)
                        start.linkTo(txtPedido.end, margin = 62.dp)
                        end.linkTo(parent.end, margin = 0.dp)
                    }
            )
            Text(
                text = "Compra de consumiveis para o almoxarifado em abril",
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(txtDescricao){
                        top.linkTo(txtSolicitacao.bottom)
                        start.linkTo(parent.start, margin = 5.dp)
                        bottom.linkTo(parent.bottom, margin = 5.dp)
                        end.linkTo(btnFavoritar.start, margin = 5.dp)
                    }
            )
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(GreyBox),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
                modifier = Modifier
                    .constrainAs(btnFavoritar){
                        top.linkTo(txtData.bottom, margin = 10.dp)
                        start.linkTo(txtDescricao.end, margin = 5.dp)
                        end.linkTo(btnDeletar.start)

                    }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                    contentDescription = "Icon favorite")
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(GreyBox),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
                modifier = Modifier
                    .constrainAs(btnDeletar){
                        top.linkTo(txtData.bottom, margin = 10.dp)
                        end.linkTo(parent.end)
                        start.linkTo(btnFavoritar.end)

                    }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = "Icon delete")
            }
        }
    }
}
@Preview
@Composable
fun SolicitacaoPreview(){
    SolicitacaoCompraItem()
}