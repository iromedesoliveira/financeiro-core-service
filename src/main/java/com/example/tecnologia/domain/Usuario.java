package com.example.tecnologia.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List; // Não esqueça deste import!

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @ToString.Exclude // Opcional: evita que a senha apareça em logs de erro
    private String senha;

    // --- COLE ISSO AQUI NO FINAL, ANTES DA ÚLTIMA CHAVE DA CLASSE ---
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;
    // ----------------------------------------------------------------
}