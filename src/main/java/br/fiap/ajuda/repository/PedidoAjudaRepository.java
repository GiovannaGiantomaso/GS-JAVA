package br.fiap.ajuda.repository;

import br.fiap.ajuda.model.PedidoAjuda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoAjudaRepository extends JpaRepository<PedidoAjuda, Long> {
}
