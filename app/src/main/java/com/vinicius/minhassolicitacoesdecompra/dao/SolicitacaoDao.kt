package com.vinicius.minhassolicitacoesdecompra.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vinicius.minhassolicitacoesdecompra.model.SolicitacaoDeCompra
import java.time.LocalDate

@Dao
interface SolicitacaoDao {

    @Insert
    fun gravar(listaSolicitacoes: MutableList<SolicitacaoDeCompra>)

    @Query("SELECT * FROM TABELA_SOLICITACOES_COMPRA ORDER BY numero_solicitacao ASC")
    fun getSolicitacoes(): MutableList<SolicitacaoDeCompra>

    @Query("SELECT * FROM tabela_solicitacoes_compra WHERE numero_solicitacao = :id")
    fun getById(id: String): SolicitacaoDeCompra?
    @Query("UPDATE tabela_solicitacoes_compra SET pedido_de_compra = :novoPedidoCompra,"+
    "descricao = :novaDescricao, status_solitacao = :novoStatus, categoria_solicitacao = :novaCategoria, armazem_destino = :novoArmazemDestino,"+
            "observacoes = :novaObservacoes, data_previsao_entrega = :novaDataPrevisaoEntrega WHERE numero_solicitacao = :id")
    fun updateSolicitacao(
        id: String,
        novoPedidoCompra: String,
        novaDescricao: String,
        novoStatus: String,
        novaCategoria: String,
        novoArmazemDestino: String,
        novaObservacoes: String,
        novaDataPrevisaoEntrega: LocalDate
    )

    @Query("DELETE FROM tabela_solicitacoes_compra WHERE numero_solicitacao = :id")
    fun deleteSolicitacao(id: Int)
}