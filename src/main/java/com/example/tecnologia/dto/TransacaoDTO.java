package com.example.tecnologia.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDTO(
                @NotBlank(message = "Descrição é obrigatória") String descricao,
                @NotNull @Positive BigDecimal valor,
                @NotNull LocalDateTime data,
                @NotBlank String categoria) {
}