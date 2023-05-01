package com.vinicius.minhassolicitacoesdecompra

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vinicius.minhassolicitacoesdecompra.constantes.Constantes
import com.vinicius.minhassolicitacoesdecompra.dao.SolicitacaoDao
import com.vinicius.minhassolicitacoesdecompra.model.SolicitacaoDeCompra

@Database(entities = [SolicitacaoDeCompra::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDataBase: RoomDatabase(){
    abstract fun solicitacaoDao(): SolicitacaoDao
    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context):AppDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    Constantes.DB_SOLICITACOES
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}