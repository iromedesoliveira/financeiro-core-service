package com.example.tecnologia.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(
        String descricao,
        BigDecimal valor,
        LocalDateTime data,
        String categoria) {
}