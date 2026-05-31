package com.example.tecnologia;

import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setId(1L);
            admin.setNome("Administrador");
            admin.setEmail("admin@exemplo.com");
            admin.setSenha("senha123");
            usuarioRepository.save(admin);
            System.out.println("Usuário admin criado com sucesso!");
        }
    }
}