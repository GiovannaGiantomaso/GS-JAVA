// Atualização do controller para permitir cadastro
package br.fiap.ajuda.controller;

import br.fiap.ajuda.model.TipoAjuda;
import br.fiap.ajuda.service.TipoAjudaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TipoAjudaController {

    private final TipoAjudaService service;

    @GetMapping("/tipos-ajuda")
    public String listarTiposAjuda(Model model) {
        List<TipoAjuda> tipos = service.listarTodos();
        model.addAttribute("tiposAjuda", tipos);
        model.addAttribute("novoTipo", new TipoAjuda());
        return "tipos-ajuda";
    }

    @PostMapping("/tipos-ajuda")
    public String salvarNovoTipo(@ModelAttribute("novoTipo") TipoAjuda tipoAjuda) {
        service.salvar(tipoAjuda);
        return "redirect:/tipos-ajuda?sucesso";
    }

    @PostMapping("/tipos-ajuda/{id}/excluir")
    public String excluirTipoAjuda(@PathVariable Long id) {
        service.excluirPorId(id);
        return "redirect:/tipos-ajuda?excluido";
    }
    @GetMapping("/tipos-ajuda/{id}/editar")
    public String editarTipoAjuda(@PathVariable Long id, Model model) {
        TipoAjuda tipoExistente = service.buscarPorId(id);
        model.addAttribute("tiposAjuda", service.listarTodos());
        model.addAttribute("novoTipo", tipoExistente);
        model.addAttribute("editando", true);
        return "tipos-ajuda";
    }

    @PostMapping("/tipos-ajuda/{id}/atualizar")
    public String atualizarTipoAjuda(@PathVariable Long id, @ModelAttribute("novoTipo") TipoAjuda tipoAjudaAtualizado) {
        TipoAjuda existente = service.buscarPorId(id);
        existente.setNome(tipoAjudaAtualizado.getNome());
        existente.setDescricao(tipoAjudaAtualizado.getDescricao());
        service.salvar(existente);
        return "redirect:/tipos-ajuda?atualizado";
    }

}
