package com.example.tecnologia.repository;

import com.example.tecnologia.domain.Lancamento;
import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.domain.PerfilInvestidor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
// REPLACE.NONE impede que o Spring troque o banco por um H2 embutido genérico
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @Sql garante que o script seja executado antes da classe de teste
@Sql(scripts = { "/schema.sql" })
// @TestPropertySource garante que estas configurações sobrescrevam QUALQUER
// outra, incluindo o Oracle
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=FALSE;MODE=MySQL;CASE_INSENSITIVE_IDENTIFIERS=TRUE",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=none"
})
class LancamentoRepositoryTest {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve buscar lançamentos por ID de usuário corretamente")
    void deveBuscarLancamentosPorUsuarioId() {
        // Arrange
        Usuario usuario = Usuario.builder()
                .nome("Irome")
                .email("irome@test.com")
                .senha("123456")
                .perfilInvestidor(PerfilInvestidor.CONSERVADOR)
                .build();

        entityManager.persist(usuario);
        entityManager.flush(); // Garante que o ID seja gerado antes de associar

        Lancamento l1 = Lancamento.builder()
                .descricao("Lancamento 1")
                .valor(new BigDecimal("100.00"))
                .dataVencimento(LocalDate.now())
                .usuario(usuario)
                .build();

        Lancamento l2 = Lancamento.builder()
                .descricao("Lancamento 2")
                .valor(new BigDecimal("200.00"))
                .dataVencimento(LocalDate.now())
                .usuario(usuario)
                .build();

        entityManager.persist(l1);
        entityManager.persist(l2);
        entityManager.flush();

        // Act
        List<Lancamento> resultado = repository.findByUsuarioId(usuario.getId());

        // Assert
        assertThat(resultado).hasSize(2);
        assertThat(resultado).extracting(Lancamento::getDescricao)
                .containsExactlyInAnyOrder("Lancamento 1", "Lancamento 2");
    }
}