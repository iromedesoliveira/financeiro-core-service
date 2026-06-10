package com.example.tecnologia;

import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            usuarioRepository.deleteAll();
            Usuario usuario = Usuario.builder()
                    .nome("Iromedes")
                    .email("iromedes@email.com")
                    .senha(passwordEncoder.encode("123456"))
                    .build();

            usuarioRepository.save(usuario);
            System.out.println(">>> USUÁRIO RESETADO E CRIADO COM SUCESSO!");
        };
    }
}