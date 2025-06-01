package br.fiap.ajuda.service;

import br.fiap.ajuda.model.TipoAjuda;
import br.fiap.ajuda.repository.TipoAjudaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoAjudaService {

    private final TipoAjudaRepository repository;


    public List<TipoAjuda> listarTodos() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public TipoAjuda buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }
    public TipoAjuda salvar(TipoAjuda tipoAjuda) {
        return repository.save(tipoAjuda);
    }
    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }

}
