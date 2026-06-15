package com.example.tecnologia.repository;

import com.example.tecnologia.domain.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    // A forma mais segura para evitar ambiguidade
    List<Lancamento> findByUsuarioId(Long id);
}