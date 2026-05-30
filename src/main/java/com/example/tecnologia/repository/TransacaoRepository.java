package com.example.tecnologia.repository;

import com.example.tecnologia.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    // Busca transações pelo ID do usuário (relacionamento definido na entidade)
    List<Transacao> findByUsuarioId(Long usuarioId);

    // Busca transações por categoria (útil para o seu plano 30/30/40)
    List<Transacao> findByCategoria(String categoria);
}