package br.fiap.ajuda.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "perfilUsuario", cascade = CascadeType.ALL)
    private List<EnderecoUsuario> enderecos;

}

