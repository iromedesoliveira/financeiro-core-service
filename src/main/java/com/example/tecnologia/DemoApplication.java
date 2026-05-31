package com.example.tecnologia;

import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Usuario usuario = Usuario.builder()
                        .nome("Iromedes")
                        .email("iromedes@email.com")
                        .senha("123456")
                        .build();

                usuarioRepository.save(usuario);
                System.out.println("Usuário inicial criado automaticamente!");
            }
        };
    }
}