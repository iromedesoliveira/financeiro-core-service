package com.example.tecnologia.repository;

import com.example.tecnologia.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // O JpaRepository já nos dá métodos como findById, save, etc.
}