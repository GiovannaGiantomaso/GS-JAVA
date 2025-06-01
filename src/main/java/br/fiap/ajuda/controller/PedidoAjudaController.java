package br.fiap.ajuda.controller;

import br.fiap.ajuda.model.PedidoAjuda;
import br.fiap.ajuda.model.PerfilUsuario;
import br.fiap.ajuda.repository.PedidoAjudaRepository;
import br.fiap.ajuda.repository.TipoAjudaRepository;
import br.fiap.ajuda.service.OAuthPerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoAjudaController {

    private final PedidoAjudaRepository pedidoRepo;
    private final TipoAjudaRepository tipoAjudaRepo;
    private final OAuthPerfilService oauthPerfilService;

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("pedidoAjuda", new PedidoAjuda());
        model.addAttribute("tiposAjuda", tipoAjudaRepo.findAll());
        return "pedidos/form";
    }

    @PostMapping("/salvar")
    public String salvarPedido(@ModelAttribute("pedidoAjuda") PedidoAjuda pedido,
                               @RequestParam("tipoAjuda.id") Long tipoAjudaId,
                               @AuthenticationPrincipal OAuth2User oauthUser) {

        PerfilUsuario perfil = oauthPerfilService.vincularOuCriarPerfil(oauthUser);
        pedido.setPerfilUsuario(perfil);
        pedido.setTipoAjuda(tipoAjudaRepo.findById(tipoAjudaId).orElseThrow());
        pedido.setDataCriacao(new java.util.Date());

        pedidoRepo.save(pedido);
        return "redirect:/pedidos?sucesso";
    }
}
