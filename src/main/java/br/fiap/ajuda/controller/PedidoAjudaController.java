package br.fiap.ajuda.controller;

import br.fiap.ajuda.model.PedidoAjuda;
import br.fiap.ajuda.model.PerfilUsuario;
import br.fiap.ajuda.repository.PedidoAjudaRepository;
import br.fiap.ajuda.repository.TipoAjudaRepository;
import br.fiap.ajuda.service.OAuthPerfilService;
import br.fiap.ajuda.service.PedidoAjudaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoAjudaController {

    private final PedidoAjudaRepository pedidoRepo;
    private final TipoAjudaRepository tipoAjudaRepo;
    private final OAuthPerfilService oauthPerfilService;
    private final PedidoAjudaService pedidoAjudaService;

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

        PerfilUsuario perfil;
        try {
            perfil = oauthPerfilService.vincularOuCriarPerfil(oauthUser);
        } catch (IllegalStateException e) {
            return "redirect:/usuarios/perfil?erroCadastro=true";
        }

        pedidoAjudaService.salvarPedido(pedido, tipoAjudaId, perfil);
        return "redirect:/pedidos?sucesso";
    }

    @GetMapping
    public String listarPedidos(Model model) {
        List<PedidoAjuda> pedidos = pedidoAjudaService.listarTodosPedidosOrdenadosPorId();
        model.addAttribute("pedidos", pedidos);
        return "pedidos/lista";
    }

}

