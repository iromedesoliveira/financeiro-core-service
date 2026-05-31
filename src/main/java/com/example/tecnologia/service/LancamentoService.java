package com.example.tecnologia.service;

import com.example.tecnologia.domain.Lancamento;
import com.example.tecnologia.repository.LancamentoRepository;
import com.example.tecnologia.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    public Lancamento salvar(Lancamento lancamento) {
        return repository.save(lancamento);
    }

    public List<Lancamento> listarTodos() {
        return repository.findAll();
    }

    public Lancamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lançamento com ID " + id + " não encontrado!"));
    }

    public void remover(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Não foi possível remover: Lançamento com ID " + id + " não encontrado.");
        }
        repository.deleteById(id);
    }

    public Lancamento atualizar(Long id, Lancamento lancamento) {
        Lancamento salvo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possível atualizar: Lançamento com ID " + id + " não encontrado!"));

        salvo.setDescricao(lancamento.getDescricao());
        salvo.setValor(lancamento.getValor());
        salvo.setDataVencimento(lancamento.getDataVencimento());

        return repository.save(salvo);
    }
}