package com.example.tecnologia.controller;

import com.example.tecnologia.model.Lucro;
import com.example.tecnologia.service.LucroService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/lucros")
public class LucroController {

    private final LucroService lucroService;

    public LucroController(LucroService lucroService) {
        this.lucroService = lucroService;
    }

    @PostMapping("/processar")
    public String processar(@RequestParam BigDecimal valor) {
        lucroService.processarNovoLucro(valor);
        return "Lucro de R$ " + valor + " processado e distribuído com sucesso!";
    }

    @GetMapping("/listar")
    public java.util.List<Lucro> listarTodos() {
        return lucroService.listarTodos();
    }
}