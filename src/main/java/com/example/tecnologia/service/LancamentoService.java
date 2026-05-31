package com.example.tecnologia.service;

import com.example.tecnologia.domain.Lancamento;
import com.example.tecnologia.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    // Método para salvar um novo lançamento
    public Lancamento salvar(Lancamento lancamento) {
        // Aqui você pode adicionar regras de negócio antes de salvar,
        // por exemplo: validar se o valor é maior que zero.
        return repository.save(lancamento);
    }

    // Método para listar todos os lançamentos
    public List<Lancamento> listarTodos() {
        return repository.findAll();
    }
}