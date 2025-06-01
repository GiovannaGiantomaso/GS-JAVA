package br.fiap.ajuda.service;

import br.fiap.ajuda.dto.PerfilUsuarioFormDto;
import br.fiap.ajuda.model.EnderecoUsuario;
import br.fiap.ajuda.model.PerfilUsuario;
import br.fiap.ajuda.repository.EnderecoUsuarioRepository;
import br.fiap.ajuda.repository.PerfilUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PerfilUsuarioService {

    private final PerfilUsuarioRepository perfilRepo;
    private final EnderecoUsuarioRepository enderecoRepo;

    public PerfilUsuario salvarComEndereco(PerfilUsuarioFormDto dto) {
        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setNome(dto.getNome());
        perfil.setEmail(dto.getEmail());
        perfil.setTelefone(dto.getTelefone());

        // Usa diretamente o LocalDate
        perfil.setDataNascimento(dto.getDataNascimento());

        perfil = perfilRepo.save(perfil);

        EnderecoUsuario endereco = new EnderecoUsuario();
        endereco.setPerfilUsuario(perfil);
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());

        enderecoRepo.save(endereco);

        return perfil;
    }


}
