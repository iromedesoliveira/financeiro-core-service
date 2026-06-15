package com.example.tecnologia;

import com.example.tecnologia.domain.PerfilInvestidor; // Importe o Enum
import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder; // Importe o PasswordEncoder
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetando o codificador de senha

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuario admin = Usuario.builder()
                    .nome("Administrador")
                    .email("admin@exemplo.com")
                    .senha(passwordEncoder.encode("senha123")) // Senha agora está criptografada
                    .perfilInvestidor(PerfilInvestidor.AGRESSIVO) // Definindo o perfil padrão
                    .build();

            usuarioRepository.save(admin);
            System.out.println(">>> USUÁRIO ADMIN CRIADO COM PERFIL AGRESSIVO!");
        }
    }
}