package br.fiap.ajuda.dto;

import lombok.Data;

@Data
public class PedidoAjudaDto {
    private Long tipoAjudaId;
    private String criancasNoLocal; // 'S' ou 'N'
    private Integer pessoasNoLocal;
    private String situacaoDeRisco;
}
