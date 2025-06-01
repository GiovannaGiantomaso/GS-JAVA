package br.fiap.ajuda.service;

import br.fiap.ajuda.model.PedidoAjuda;
import br.fiap.ajuda.model.PerfilUsuario;
import br.fiap.ajuda.model.TipoAjuda;
import br.fiap.ajuda.repository.PedidoAjudaRepository;
import br.fiap.ajuda.repository.TipoAjudaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoAjudaService {

    private final PedidoAjudaRepository pedidoRepo;
    private final TipoAjudaRepository tipoAjudaRepo;

    public void salvarPedido(PedidoAjuda pedido, Long tipoAjudaId, PerfilUsuario perfil) {
        TipoAjuda tipo = tipoAjudaRepo.findById(tipoAjudaId).orElseThrow();
        pedido.setTipoAjuda(tipo);
        pedido.setPerfilUsuario(perfil);
        pedido.setDataCriacao(new Date());
        pedidoRepo.save(pedido);
    }
    public List<PedidoAjuda> listarTodosPedidosOrdenadosPorId() {
        return pedidoRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}

