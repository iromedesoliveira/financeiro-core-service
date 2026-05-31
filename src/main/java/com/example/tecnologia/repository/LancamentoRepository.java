package com.example.tecnologia.repository;

import com.example.tecnologia.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    // O JpaRepository já nos dá métodos como save, findById, findAll, etc.
}