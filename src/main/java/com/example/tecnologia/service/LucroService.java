package com.example.tecnologia.service;

import com.example.tecnologia.model.Lucro;
import com.example.tecnologia.repository.LucroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class LucroService {

    private final LucroRepository repository;

    public LucroService(LucroRepository repository) {
        this.repository = repository;
    }

    public java.util.List<Lucro> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public void processarNovoLucro(BigDecimal valorTotal) {

        if (valorTotal == null || valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do lucro deve ser positivo.");
        }
        // Aplicação da sua estratégia de 30/30/40
        BigDecimal savings = valorTotal.multiply(new BigDecimal("0.30"));
        BigDecimal dividendos = valorTotal.multiply(new BigDecimal("0.30"));
        BigDecimal reinvestimento = valorTotal.multiply(new BigDecimal("0.40"));

        Lucro lucro = new Lucro();
        lucro.setValorOriginal(valorTotal);
        lucro.setSavings(savings);
        lucro.setDividendos(dividendos);
        lucro.setReinvestimento(reinvestimento);
        lucro.setDataRegistro(LocalDateTime.now());

        repository.save(lucro);
    }
}