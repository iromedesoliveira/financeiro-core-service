package com.example.tecnologia.controller;

import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.dto.LancamentoDTO;
import com.example.tecnologia.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Financeiro", description = "Endpoints para gerenciamento de lançamentos e investimentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    // Endpoint principal de Distribuição (Seguro e usando o usuário logado)
    @PostMapping("/distribuir")
    @Operation(summary = "Distribui investimento", description = "Executa a lógica de distribuição para o usuário logado via Token JWT")
    public ResponseEntity<String> distribuirInvestimento(
            @RequestParam BigDecimal valor,
            @AuthenticationPrincipal Usuario usuario) {

        service.realizarDistribuicaoInvestimento(usuario.getId(), valor);
        return ResponseEntity.ok("Investimento de R$ " + valor + " distribuído com sucesso!");
    }

    @PostMapping
    @Operation(summary = "Cria um novo lançamento", description = "Persiste um lançamento financeiro no banco de dados")
    public ResponseEntity<LancamentoDTO> criar(@Valid @RequestBody LancamentoDTO dto) {
        LancamentoDTO salvo = service.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    @Operation(summary = "Lista todos os lançamentos")
    public ResponseEntity<List<LancamentoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodosDTO());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca lançamento por ID")
    public ResponseEntity<LancamentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorIdDTO(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um lançamento existente")
    public ResponseEntity<LancamentoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LancamentoDTO dto) {
        LancamentoDTO salvo = service.atualizar(id, dto);
        return ResponseEntity.ok(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove um lançamento")
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}