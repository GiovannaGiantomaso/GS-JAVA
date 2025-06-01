package br.fiap.ajuda.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PERFILUSUARIO")
public class PerfilUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    private String telefone;

    @Column(name = "DATANASCIMENTO")
    private LocalDate dataNascimento;

    @OneToOne(mappedBy = "perfilUsuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EnderecoUsuario endereco;
}
