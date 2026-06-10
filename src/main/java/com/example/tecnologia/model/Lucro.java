package com.example.tecnologia.model;

import jakarta.persistence.*;
import lombok.Data; // Certifique-se de que a dependência do Lombok está no pom.xml
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "LUCROS")
@Data
public class Lucro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_original")
    private BigDecimal valorOriginal;

    @Column(name = "savings")
    private BigDecimal savings;

    @Column(name = "dividendos")
    private BigDecimal dividendos;

    @Column(name = "reinvestimento")
    private BigDecimal reinvestimento;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;
}