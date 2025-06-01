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

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class PerfilUsuarioController {

    private final PerfilUsuarioService perfilUsuarioService;

    @GetMapping("/perfil")
    public String exibirFormulario(Model model) {
        model.addAttribute("perfilUsuario", new PerfilUsuarioFormDto());
        return "usuarios/perfil-form";
    }


    @PostMapping("/salvar")
    public String salvarPerfil(@ModelAttribute("perfilUsuario") PerfilUsuarioFormDto dto,
                               @AuthenticationPrincipal OAuth2User usuarioOauth,
                               HttpSession session) {

        PerfilUsuario perfil = perfilUsuarioService.salvarComEndereco(dto);

        String emailGoogle = usuarioOauth.getAttribute("email");
        session.setAttribute("usuarioEmail", emailGoogle);

        return "redirect:/principal?sucesso";
    }

}
