package com.example.tecnologia.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class LancamentoDTO {
    private Long id;
    private String descricao;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private Long usuarioId; // Note que aqui usamos apenas o ID, não o objeto Usuario

}