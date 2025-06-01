package br.fiap.ajuda.service;

import br.fiap.ajuda.model.PerfilUsuario;
import br.fiap.ajuda.repository.PerfilUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthPerfilService {

    private final PerfilUsuarioRepository perfilRepo;

    public PerfilUsuario vincularOuCriarPerfil(OAuth2User oauthUser) {
        String email = oauthUser.getAttribute("email");

        Optional<PerfilUsuario> existente = perfilRepo.findByEmail(email);
        return existente.orElseThrow(() ->
                new IllegalStateException("Perfil não encontrado para o e-mail: " + email +
                        ". Você precisa completar seu cadastro."));
    }
}
