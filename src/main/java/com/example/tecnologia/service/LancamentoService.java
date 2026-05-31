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

    // Métodos que já existem
    public Lancamento salvar(Lancamento lancamento) {
        return repository.save(lancamento);
    }

    public List<Lancamento> listarTodos() {
        return repository.findAll();
    }

    // Novos métodos de DELETE e UPDATE
    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Lancamento atualizar(Long id, Lancamento lancamento) {
        Lancamento salvo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        salvo.setDescricao(lancamento.getDescricao());
        salvo.setValor(lancamento.getValor());
        salvo.setDataVencimento(lancamento.getDataVencimento());

        return repository.save(salvo);
    }
}