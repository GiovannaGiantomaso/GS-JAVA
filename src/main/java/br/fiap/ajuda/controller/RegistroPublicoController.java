package br.fiap.ajuda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class RegistroPublicoController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "usuarios/login";
    }

}
