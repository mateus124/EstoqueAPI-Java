package dev.mateus124.Estoque.dto;
import java.time.LocalDateTime;

public class EntradaEstoqueRequest {
    private LocalDateTime dataEntrada;
    private Integer quantidade;
    private Long produtoId;
    private Long fornecedorId;
    private String descricao;

    public EntradaEstoqueRequest() {
    }

    public EntradaEstoqueRequest(LocalDateTime dataEntrada, Integer quantidade, Long produtoId, Long fornecedorId, String descricao) {
        this.dataEntrada = dataEntrada;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.fornecedorId = fornecedorId;
        this.descricao = descricao;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
