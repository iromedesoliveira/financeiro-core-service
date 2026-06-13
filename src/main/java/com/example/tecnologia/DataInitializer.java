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
        // Verifica se não há usuários para não duplicar o admin a cada inicialização
        if (usuarioRepository.count() == 0) {
            Usuario admin = Usuario.builder()
                    .nome("Administrador")
                    .email("admin@exemplo.com")
                    .senha("senha123")
                    .build();

            usuarioRepository.save(admin);
            System.out.println(">>> USUÁRIO ADMIN CRIADO COM SUCESSO!");
        }
    }
}