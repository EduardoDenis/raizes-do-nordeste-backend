package br.com.raizesdonordeste.backend.api.pedido.dto;

public class PagamentoRequestDTO {
    private String statusPagamento; // Deve receber "APROVADO" ou "RECUSADO"

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }
}