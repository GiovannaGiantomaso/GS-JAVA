package br.fiap.ajuda.controller;

import br.fiap.ajuda.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ai")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/perguntar")
    public String perguntar(@RequestParam("pergunta") String pergunta, Model model) {
        String resposta = chatService.enviarMensagem(pergunta);
        model.addAttribute("pergunta", pergunta);
        model.addAttribute("resposta", resposta);
        return "resposta_ia";
    }

    @GetMapping("/form")
    public String formularioPergunta() {
        return "perguntar";
    }

}
