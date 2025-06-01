package br.fiap.ajuda.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PEDIDOAJUDA")
public class PedidoAjuda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PERFILUSUARIOID", nullable = false)
    private PerfilUsuario perfilUsuario;

    @ManyToOne
    @JoinColumn(name = "TIPOAJUDAID", nullable = false)
    private TipoAjuda tipoAjuda;

    @Column(name = "DATACRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();

    @Column(name = "CRIANCASNOLOCAL", length = 1, nullable = false)
    private String criancasNoLocal; // 'S' ou 'N'

    @Column(name = "PESSOASNOLOCAL")
    private Integer pessoasNoLocal;

    @Column(name = "SITUACAODERISCO")
    private String situacaoDeRisco;
}
