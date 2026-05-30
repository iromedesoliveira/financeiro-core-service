package com.example.tecnologia.controller;

import com.example.tecnologia.model.Transacao;
import com.example.tecnologia.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @GetMapping
    public List<Transacao> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Transacao criar(@RequestBody Transacao transacao) {
        return repository.save(transacao);
    }
}