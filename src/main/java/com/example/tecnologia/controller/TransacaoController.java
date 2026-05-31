package com.example.tecnologia.controller;

import com.example.tecnologia.domain.Transacao;
import com.example.tecnologia.service.TransacaoService;
import com.example.tecnologia.dto.TransacaoDTO; // Apenas este import é necessário
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @GetMapping
    public List<Transacao> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Transacao buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Transacao criar(@RequestBody TransacaoDTO dto) {
        return service.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}