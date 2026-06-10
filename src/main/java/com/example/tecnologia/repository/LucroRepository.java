package com.example.tecnologia.repository;

import com.example.tecnologia.model.Lucro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LucroRepository extends JpaRepository<Lucro, Long> {
    // O JpaRepository já nos dá métodos como save, findAll, findById, delete, etc.
}