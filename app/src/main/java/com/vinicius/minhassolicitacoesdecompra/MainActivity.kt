package com.vinicius.minhassolicitacoesdecompra

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vinicius.minhassolicitacoesdecompra.ui.theme.MInhasSolicitacoesDeCompraTheme
import com.vinicius.minhassolicitacoesdecompra.view.AdicionarSolicitacao
import com.vinicius.minhassolicitacoesdecompra.view.EditarSolicitacao
import com.vinicius.minhassolicitacoesdecompra.view.Home

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    composable("editarSolicitacao"){
                        EditarSolicitacao(navController = navController)
                    }
                }
            }
        }
    }
}

