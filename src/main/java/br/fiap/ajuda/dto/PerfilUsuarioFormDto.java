package br.fiap.ajuda.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PerfilUsuarioFormDto {
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}

