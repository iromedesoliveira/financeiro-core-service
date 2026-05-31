package com.example.tecnologia.repository;

import com.example.tecnologia.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Agora você tem a funcionalidade de buscar por email no seu repositório
    // oficial
    Optional<Usuario> findByEmail(String email);
}