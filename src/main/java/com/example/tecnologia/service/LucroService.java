package com.example.tecnologia.service;

import com.example.tecnologia.model.Lucro;
import com.example.tecnologia.repository.LucroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LucroService {

    private final LucroRepository repository;

    public LucroService(LucroRepository repository) {
        this.repository = repository;
    }

    public List<Lucro> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public void processarNovoLucro(BigDecimal valorTotal, String perfil) {

        if (valorTotal == null || valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do lucro deve ser positivo.");
        }

        // A lógica agora exige um perfil válido ou lança exceção
        BigDecimal[] percentuais = definirPercentuais(perfil);

        Lucro lucro = new Lucro();
        lucro.setValorOriginal(valorTotal);
        lucro.setSavings(valorTotal.multiply(percentuais[0]));
        lucro.setDividendos(valorTotal.multiply(percentuais[1]));
        lucro.setReinvestimento(valorTotal.multiply(percentuais[2]));
        lucro.setDataRegistro(LocalDateTime.now());

        repository.save(lucro);
    }

    private BigDecimal[] definirPercentuais(String perfil) {
        if (perfil == null || perfil.isBlank()) {
            throw new IllegalArgumentException("O perfil de investidor é obrigatório e não pode ser vazio.");
        }

        return switch (perfil.toUpperCase()) {
            case "CONSERVADOR" -> new BigDecimal[] { new BigDecimal("0.80"), new BigDecimal("0.20"), BigDecimal.ZERO };
            case "MODERADO" ->
                new BigDecimal[] { new BigDecimal("0.50"), new BigDecimal("0.40"), new BigDecimal("0.10") };
            case "AGRESSIVO" ->
                new BigDecimal[] { new BigDecimal("0.30"), new BigDecimal("0.30"), new BigDecimal("0.40") };
            default -> throw new IllegalArgumentException("Perfil de investidor inválido: " + perfil);
        };
    }
}