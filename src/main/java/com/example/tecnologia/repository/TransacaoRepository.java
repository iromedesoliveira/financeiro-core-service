package com.example.tecnologia.repository;

import com.example.tecnologia.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    // Aqui o Spring já nos dá automaticamente métodos como:
    // save(), findAll(), findById(), deleteById(), etc.
}