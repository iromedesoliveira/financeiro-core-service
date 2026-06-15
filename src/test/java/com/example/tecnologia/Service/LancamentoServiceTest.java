package com.example.tecnologia.service;

import com.example.tecnologia.domain.Lancamento;
import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.repository.LancamentoRepository;
import com.example.tecnologia.repository.LucroRepository;
import com.example.tecnologia.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LancamentoServiceTest {

    private LancamentoRepository repository;
    private UsuarioRepository usuarioRepository;
    private LucroRepository lucroRepository;
    private LancamentoService service;

    @BeforeEach
    void setUp() {
        repository = mock(LancamentoRepository.class);
        usuarioRepository = mock(UsuarioRepository.class);
        lucroRepository = mock(LucroRepository.class);
        service = new LancamentoService(repository, usuarioRepository, lucroRepository);
    }

    @Test
    @DisplayName("Deve realizar a distribuição de investimentos corretamente")
    void deveRealizarDistribuicaoInvestimento() {
        // Arrange
        Long usuarioId = 1L;
        BigDecimal valorTotal = new BigDecimal("1000.00");
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        // CORREÇÃO: O mock deve retornar a entidade com um ID para evitar o
        // NullPointerException
        // no método toDTO() do seu service.
        when(repository.save(any(Lancamento.class))).thenAnswer(invocation -> {
            Lancamento entity = invocation.getArgument(0);
            if (entity.getId() == null) {
                entity.setId(1L); // Simula o ID gerado pelo banco
            }
            return entity;
        });

        // Act
        service.realizarDistribuicaoInvestimento(usuarioId, valorTotal);

        // Assert
        verify(repository, times(3)).save(any(Lancamento.class));
        verify(lucroRepository, times(1)).save(any());
    }
}