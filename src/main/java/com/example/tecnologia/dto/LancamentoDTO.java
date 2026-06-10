package com.example.tecnologia.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoDTO {
    private Long id;
    private String descricao;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private Long usuarioId;
}