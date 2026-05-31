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

    // 1. MÉTODO POST (Já existia)
    @PostMapping
    public ResponseEntity<Lancamento> criar(@RequestBody Lancamento lancamento) {
        Lancamento salvo = service.salvar(lancamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // 2. MÉTODO GET (Já existia)
    @GetMapping
    public ResponseEntity<List<Lancamento>> listar() {
        List<Lancamento> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // 3. NOVO MÉTODO DELETE (Adicione abaixo do listar)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }

    // 4. NOVO MÉTODO PUT (Adicione abaixo do remover)
    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @RequestBody Lancamento lancamento) {
        Lancamento salvo = service.atualizar(id, lancamento);
        return ResponseEntity.ok(salvo);
    }
}