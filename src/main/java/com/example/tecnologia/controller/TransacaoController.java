package com.example.tecnologia.controller;

import com.example.tecnologia.domain.Transacao;
import com.example.tecnologia.dto.TransacaoDTO;
import com.example.tecnologia.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.tecnologia.dto.TransacaoDTO;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service; // Agora o controller conversa com o Service, não com o Repositório

    @GetMapping
    public List<Transacao> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Transacao criar(@RequestBody TransacaoDTO dto) { // Recebemos um DTO, não a Entidade
        return service.salvar(dto);
    }
}