package com.example.tecnologia.controller;

import com.example.tecnologia.service.LucroService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/lucros")
public class LucroController {

    private final LucroService lucroService;

    public LucroController(LucroService lucroService) {
        this.lucroService = lucroService;
    }

    @PostMapping("/processar")
    public String processar(@RequestParam BigDecimal valor) {
        System.out.println(">>> Recebi o valor: " + valor); // Adicione isso
        lucroService.processarNovoLucro(valor);
        System.out.println(">>> Terminei de processar."); // Adicione isso
        return "Lucro de R$ " + valor + " processado e distribuído com sucesso!";
    }
}