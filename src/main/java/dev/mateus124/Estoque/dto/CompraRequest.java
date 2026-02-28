package dev.mateus124.Estoque.dto;

public class CompraRequest {
    private String nomeCliente;
    private Long produtoId;
    private Integer quantidade;

    public CompraRequest() {
    }

    public CompraRequest(String nomeCliente, Long produtoId, Integer quantidade) {
        this.nomeCliente = nomeCliente;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
