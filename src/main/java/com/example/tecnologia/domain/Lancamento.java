package com.example.tecnologia.domain;

import jakarta.persistence.*; // Se estiver usando Spring Boot 3+ (Jakarta)
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "LANCAMENTO") // Nome exato da sua tabela no Oracle
public class Lancamento {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lancamento_seq")
    @SequenceGenerator(name = "lancamento_seq", sequenceName = "SQ_LANCAMENTO", allocationSize = 1)
    private Long id;

    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;

    @Column(name = "DATA_VENCIMENTO", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "VALOR", nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    // Construtores, Getters e Setters
    public Lancamento() {
    }

    // ... adicione os getters e setters aqui
}