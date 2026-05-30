package com.example.tecnologia.domain;

import jakarta.persistence.*;
import lombok.*; // Importa todas as anotações do Lombok

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter // Gera todos os getters e setters automaticamente
@NoArgsConstructor // Gera construtor vazio
@AllArgsConstructor // Gera construtor com todos os campos
@Builder // Padrão Sênior: permite criar objetos de forma fluida
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;
}