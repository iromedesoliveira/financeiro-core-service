package com.example.tecnologia.domain;

import jakarta.persistence.*;
import lombok.*; // Usando Lombok para reduzir o código gigante de Getters/Setters
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "LANCAMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lancamento_seq")
    @SequenceGenerator(name = "lancamento_seq", sequenceName = "SQ_LANCAMENTO", allocationSize = 1)
    private Long id;

    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;

    @Column(name = "DATA_VENCIMENTO", nullable = true)
    private LocalDate dataVencimento;

    @Column(name = "VALOR", nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @ManyToOne(optional = true) // <--- O "optional = true" é a chave aqui
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}