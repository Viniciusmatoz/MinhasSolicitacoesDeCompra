package com.vinicius.minhassolicitacoesdecompra.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vinicius.minhassolicitacoesdecompra.constantes.Constantes
import java.time.LocalDate

@Entity(tableName = Constantes.TABELA_SOLICITACOES_COMPRA)
data class SolicitacaoDeCompra (
        @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "numero_solicitacao") val numeroSolicitacao: String,
    @ColumnInfo(name = "pedido_de_compra") val numeroPedido: String,
    @ColumnInfo(name = "descricao") val descricao: String,
    @ColumnInfo(name = "status_solitacao") val statusSolicitacao: String,
    @ColumnInfo(name = "categoria_solicitacao") val categoriaSolicitacao: String,
    @ColumnInfo(name = "armazem_destino") val armazemDestino: String,
    @ColumnInfo(name = "observacoes") val observacoesSolicitacao: String,
    @ColumnInfo(name = "data_previsao_entrega") val dataPrevisaoEntrega: LocalDate,
        )

