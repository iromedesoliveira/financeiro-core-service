package com.example.tecnologia.controller;

import com.example.tecnologia.dto.LancamentoDTO; // Importante: usar o DTO
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
    public ResponseEntity<LancamentoDTO> criar(@RequestBody LancamentoDTO dto) {
        LancamentoDTO salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodosDTO());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDTO> atualizar(@PathVariable Long id, @RequestBody LancamentoDTO dto) {
        LancamentoDTO salvo = service.atualizar(id, dto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorIdDTO(id));
    }
}