package br.fiap.ajuda.service;

import br.fiap.ajuda.messaging.TipoAjudaProducer;
import br.fiap.ajuda.model.TipoAjuda;
import br.fiap.ajuda.repository.TipoAjudaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoAjudaService {

    private final TipoAjudaRepository repository;
    private final TipoAjudaProducer producer;

    public List<TipoAjuda> listarTodos() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public TipoAjuda buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public TipoAjuda salvar(TipoAjuda tipoAjuda) {
        boolean isNovo = tipoAjuda.getId() == null;
        TipoAjuda salvo = repository.save(tipoAjuda);

        if (isNovo) {
            producer.enviarTipoAjudaCriado(salvo);
        } else {
            System.out.println(" TipoAjuda atualizado:");
            System.out.println("   → ID: " + salvo.getId());
            System.out.println("   → Nome: " + salvo.getNome());
            System.out.println("   → Descrição: " + salvo.getDescricao());
        }

        return salvo;
    }

    public void excluirPorId(Long id) {
        TipoAjuda excluido = buscarPorId(id);
        repository.deleteById(id);
        System.out.println(" TipoAjuda excluído:");
        System.out.println("   → ID: " + excluido.getId());
        System.out.println("   → Nome: " + excluido.getNome());
    }
}
