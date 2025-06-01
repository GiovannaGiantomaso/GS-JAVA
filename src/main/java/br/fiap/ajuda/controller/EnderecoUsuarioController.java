package br.fiap.ajuda.controller;

import br.fiap.ajuda.model.EnderecoUsuario;
import br.fiap.ajuda.model.PerfilUsuario;
import br.fiap.ajuda.repository.EnderecoUsuarioRepository;
import br.fiap.ajuda.repository.PerfilUsuarioRepository;
import br.fiap.ajuda.service.OAuthPerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/endereco")
public class EnderecoUsuarioController {

    private final EnderecoUsuarioRepository enderecoRepo;
    private final OAuthPerfilService oauthPerfilService;

    @GetMapping("/novo")
    public String exibirFormulario() {
        return "endereco/form";
    }

    @PostMapping("/salvar")
    public String salvarEndereco(@ModelAttribute EnderecoUsuario endereco,
                                 @AuthenticationPrincipal OAuth2User oauthUser) {

        PerfilUsuario perfil = oauthPerfilService.vincularOuCriarPerfil(oauthUser);
        endereco.setPerfilUsuario(perfil);
        enderecoRepo.save(endereco);

        return "redirect:/pedidos/novo";
    }
}
