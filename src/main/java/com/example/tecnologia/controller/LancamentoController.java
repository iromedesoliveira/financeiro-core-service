package com.example.tecnologia.controller;

import com.example.tecnologia.domain.Lancamento;
import com.example.tecnologia.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    @PostMapping
    public ResponseEntity<Lancamento> criar(@RequestBody Lancamento lancamento) {
        Lancamento salvo = service.salvar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> listar() {
        List<Lancamento> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }
}