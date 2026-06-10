package com.example.tecnologia.controller;

import com.example.tecnologia.dto.LancamentoDTO;
import com.example.tecnologia.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    // Métodos CRUD existentes
    @PostMapping
    public ResponseEntity<LancamentoDTO> criar(@Valid @RequestBody LancamentoDTO dto) {
        LancamentoDTO salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodosDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorIdDTO(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LancamentoDTO dto) {
        LancamentoDTO salvo = service.atualizar(id, dto);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }

    // NOVO: Endpoint para realizar a distribuição automática de 100 mil (ou
    // qualquer valor)
    @PostMapping("/distribuir/{usuarioId}")
    public ResponseEntity<String> distribuirInvestimento(
            @PathVariable Long usuarioId,
            @RequestParam BigDecimal valor) {

        service.realizarDistribuicaoInvestimento(usuarioId, valor);
        return ResponseEntity.ok("Investimento de R$ " + valor + " distribuído com sucesso!");
    }
}