package com.vinicius.minhassolicitacoesdecompra

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vinicius.minhassolicitacoesdecompra.ui.theme.MInhasSolicitacoesDeCompraTheme
import com.vinicius.minhassolicitacoesdecompra.view.AdicionarSolicitacao
import com.vinicius.minhassolicitacoesdecompra.view.EditarSolicitacaoCompra
import com.vinicius.minhassolicitacoesdecompra.view.Home

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this
        initializeDatabase(context)

        setContent {
            MInhasSolicitacoesDeCompraTheme{
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home" ){
                    composable("home"){
                        Home(navController)
                    }
                    composable("adicionarSolicitacao"){
                        AdicionarSolicitacao(navController = navController)
                    }
                   composable(
                        "editarSolicitacao/{numeroSolicitacao}",
                        arguments = listOf(navArgument("numeroSolicitacao") {})
                    ){
                        EditarSolicitacaoCompra(
                            navController = navController,
                            it.arguments?.getString("numeroSolicitacao").toString()
                        )
                    }
                }
            }
        }
    }
    private fun initializeDatabase(context: Context) {
        val solicitacaoDao = AppDataBase.getInstance(context).solicitacaoDao()
        // Restante do código de inicialização do banco de dados...
    }
}

