package com.example.tecnologia.controller;

import com.example.tecnologia.model.Lucro;
import com.example.tecnologia.service.LucroService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lucros")
@CrossOrigin(origins = "http://localhost:5173")
public class LucroController {

    private final LucroService lucroService;

    public LucroController(LucroService lucroService) {
        this.lucroService = lucroService;
    }

    @PostMapping("/processar")
    public String processar(@RequestBody Map<String, Object> payload) {
        BigDecimal valor = new BigDecimal(payload.get("valorTotal").toString());
        String perfil = payload.get("perfil").toString();

        lucroService.processarNovoLucro(valor, perfil);
        return "Lucro de R$ " + valor + " processado no perfil " + perfil + " com sucesso!";
    }

    @GetMapping("/listar")
    public List<Lucro> listarTodos() {
        return lucroService.listarTodos();
    }
}