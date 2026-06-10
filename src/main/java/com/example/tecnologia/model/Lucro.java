package com.example.tecnologia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "LUCROS")
@Data
public class Lucro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorOriginal;
    private BigDecimal savings;
    private BigDecimal dividendos;
    private BigDecimal reinvestimento;
    private LocalDateTime dataRegistro;

    // Construtores, Getters e Setters...
}