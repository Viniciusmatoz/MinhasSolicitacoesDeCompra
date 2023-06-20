package com.vinicius.minhassolicitacoesdecompra.itemlista

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vinicius.minhassolicitacoesdecompra.AppDataBase
import com.vinicius.minhassolicitacoesdecompra.R
import com.vinicius.minhassolicitacoesdecompra.model.SolicitacaoDeCompra
import com.vinicius.minhassolicitacoesdecompra.ui.theme.BlueCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreenCircle
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyCardBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.GreyTextBox
import com.vinicius.minhassolicitacoesdecompra.ui.theme.Purple80
import com.vinicius.minhassolicitacoesdecompra.ui.theme.RedCircle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitacaoCompraItem(
    navController: NavController,
    position: Int,
    listaSolicitacoes: MutableList<SolicitacaoDeCompra>,
    context: Context
){

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val dao = AppDataBase.getInstance(context).solicitacaoDao()

    val solicitacao = listaSolicitacoes[position]

    val numeroSolicitacao = listaSolicitacoes[position].numeroSolicitacao
    val numeroPedido = listaSolicitacoes[position].numeroPedido
    val statusSolicitacao = listaSolicitacoes[position].statusSolicitacao
    val descricaoSolicitacao = listaSolicitacoes[position].descricao
    val dataCriacao = listaSolicitacoes[position].dataCriacao

    fun alertDialogDeleteSolicitacao(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog
            .setTitle("Excluir Solicitação")
            .setMessage("Tem certeza?")
        alertDialog.setPositiveButton("OK"){_,_->
            scope.launch(Dispatchers.IO){
                dao.deleteSolicitacao(numeroSolicitacao)
                listaSolicitacoes.remove(solicitacao)
            }
            scope.launch(Dispatchers.Main){
                navController.navigate("home")
                Toast.makeText(context,"Solicitação removida com sucesso",Toast.LENGTH_SHORT).show()
            }
        }
        alertDialog.setNegativeButton("Cancelar"){_,_->}
        alertDialog.show()
    }


    Card(
        onClick = {navController.navigate("editarSolicitacao/${numeroSolicitacao}")},
        colors = CardDefaults.cardColors(GreyCardBox),
        shape = ShapeDefaults.Small,
        elevation = CardDefaults.cardElevation(15.dp),
        modifier = Modifier
            .padding(top = 12.dp, start = 10.dp, end = 10.dp, bottom = 0.dp)
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
                        val circleColor = when (statusSolicitacao) {
                            "SC em andamento" -> RedCircle
                            "PC em aprovação" -> BlueCircle
                            "Aguardando entrega" -> GreenCircle
                            else -> Purple80
                        }
                        drawCircle(circleColor)
                    }
                )

                Row(modifier = Modifier.weight(1f).padding(start = 6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "SC $numeroSolicitacao",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top = 5.dp, start = 10.dp)
                    )
                    Text(
                        text = "PC $numeroPedido".take(9),
                        color = GreyTextBox,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .padding(top = 5.dp, start = 15.dp)
                            .weight(1f)
                    )
                }
                Text(
                    text = dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 2.dp, end = 5.dp)
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = descricaoSolicitacao,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(start = 10.dp)
                )
                IconButton(onClick = {
                    alertDialogDeleteSolicitacao()
                },
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                ){
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                        contentDescription = "icon delete")
                }

            }
        }
    }
}