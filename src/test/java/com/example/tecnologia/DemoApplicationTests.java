package com.example.tecnologia;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Este teste garante que o contexto do Spring carregue corretamente com as
 * configurações de teste.
 * É a primeira linha de defesa contra erros de configuração no
 * application-test.properties.
 */
@SpringBootTest
@ActiveProfiles("test") // Garante que o Spring carregue apenas o perfil de teste (H2)
class DemoApplicationTests {

	@Test
	void contextLoads() {
		// Se este teste passar, significa que:
		// 1. O Spring conseguiu subir o contexto.
		// 2. As dependências (Services, Controllers, Repositories) foram injetadas.
		// 3. O banco H2 foi conectado com sucesso.
	}

}