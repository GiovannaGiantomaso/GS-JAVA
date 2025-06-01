// Atualização do controller para permitir cadastro
package br.fiap.ajuda.controller;

import br.fiap.ajuda.model.TipoAjuda;
import br.fiap.ajuda.service.TipoAjudaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TipoAjudaController {

    private final TipoAjudaService service;

    @GetMapping("/tipos-ajuda")
    public String listarTiposAjuda(Model model) {
        List<TipoAjuda> tipos = service.listarTodos();
        System.out.println("Tipos do banco: " + tipos);
        model.addAttribute("tiposAjuda", tipos);
        model.addAttribute("novoTipo", new TipoAjuda());
        return "tipos-ajuda";
    }


    @PostMapping("/tipos-ajuda")
    public String salvarNovoTipo(@ModelAttribute("novoTipo") TipoAjuda tipoAjuda) {
        service.salvar(tipoAjuda);
        return "redirect:/tipos-ajuda?sucesso";
    }
}
