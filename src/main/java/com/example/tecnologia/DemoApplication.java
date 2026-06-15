package com.example.tecnologia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        Environment env = context.getEnvironment();

        log.info("--- APLICAÇÃO INICIADA COM SUCESSO ---");
        log.info("Perfis ativos: {}", Arrays.toString(env.getActiveProfiles()));
        log.info("URL do Banco configurada: {}", env.getProperty("spring.datasource.url"));

        // Dica: Adicione este aviso se o perfil estiver vazio para evitar surpresas
        if (env.getActiveProfiles().length == 0) {
            log.warn("NENHUM PERFIL ATIVO DEFINIDO! A aplicação está usando as configurações padrão.");
        }
    }
}