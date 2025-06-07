package br.fiap.ajuda.service;

import br.fiap.ajuda.dto.PerfilUsuarioFormDto;
import br.fiap.ajuda.model.EnderecoUsuario;
import br.fiap.ajuda.model.PerfilUsuario;
import br.fiap.ajuda.repository.EnderecoUsuarioRepository;
import br.fiap.ajuda.repository.PerfilUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerfilUsuarioService {

    private final PerfilUsuarioRepository perfilRepo;
    private final EnderecoUsuarioRepository enderecoRepo;

    public PerfilUsuario salvarComEndereco(PerfilUsuarioFormDto dto) {
        Optional<PerfilUsuario> perfilExistente = perfilRepo.findByEmail(dto.getEmail());

        PerfilUsuario perfil;
        if (perfilExistente.isPresent()) {
            perfil = perfilExistente.get();
            perfil.setNome(dto.getNome());
            perfil.setTelefone(dto.getTelefone());
            perfil.setDataNascimento(dto.getDataNascimento());
        } else {
            perfil = new PerfilUsuario();
            perfil.setNome(dto.getNome());
            perfil.setEmail(dto.getEmail());
            perfil.setTelefone(dto.getTelefone());
            perfil.setDataNascimento(dto.getDataNascimento());
        }

        perfil = perfilRepo.save(perfil);

        EnderecoUsuario endereco;
        if (perfil.getEndereco() != null) {
            endereco = perfil.getEndereco();
        } else {

            endereco = new EnderecoUsuario();
            endereco.setPerfilUsuario(perfil);
        }

        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());

        enderecoRepo.save(endereco);

        return perfil;
    }


    public Optional<PerfilUsuario> buscarPorEmail(String email) {
        return perfilRepo.findByEmail(email);
    }

    public PerfilUsuario salvar(PerfilUsuario perfil) {
        return perfilRepo.save(perfil);
    }
}
