package br.fiap.ajuda.controller;

import br.fiap.ajuda.dto.PerfilUsuarioFormDto;
import br.fiap.ajuda.model.PerfilUsuario;
import br.fiap.ajuda.service.PerfilUsuarioService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class PerfilUsuarioController {

    private final PerfilUsuarioService perfilUsuarioService;

    @GetMapping("/perfil")
    public String exibirFormularioPerfil(Model model, @AuthenticationPrincipal OAuth2User oauthUser) {
        String email = oauthUser.getAttribute("email");

        Optional<PerfilUsuario> perfilExistente = perfilUsuarioService.buscarPorEmail(email);

        PerfilUsuarioFormDto dto = new PerfilUsuarioFormDto();
        perfilExistente.ifPresent(perfil -> {
            dto.setNome(perfil.getNome());
            dto.setEmail(perfil.getEmail());
            dto.setTelefone(perfil.getTelefone());
            dto.setDataNascimento(perfil.getDataNascimento());

            if (perfil.getEndereco() != null) {
                dto.setLogradouro(perfil.getEndereco().getLogradouro());
                dto.setNumero(perfil.getEndereco().getNumero());
                dto.setBairro(perfil.getEndereco().getBairro());
                dto.setCidade(perfil.getEndereco().getCidade());
                dto.setEstado(perfil.getEndereco().getEstado());
                dto.setCep(perfil.getEndereco().getCep());
            }
        });

        model.addAttribute("perfilUsuario", dto);
        return "usuarios/perfil-form";
    }


    @PostMapping("/perfil")
    public String salvarPerfil(@ModelAttribute("perfilUsuario") PerfilUsuarioFormDto dto,
                               @AuthenticationPrincipal OAuth2User oauthUser) {
        dto.setEmail(oauthUser.getAttribute("email"));
        perfilUsuarioService.salvarComEndereco(dto);
        return "redirect:/principal?sucesso";
    }

}
