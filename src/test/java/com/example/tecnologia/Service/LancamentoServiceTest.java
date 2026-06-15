package com.example.tecnologia.service;

import com.example.tecnologia.domain.Lancamento;
import com.example.tecnologia.domain.Usuario;
import com.example.tecnologia.repository.LancamentoRepository;
import com.example.tecnologia.repository.LucroRepository;
import com.example.tecnologia.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.math.RoundingMode; // Importante para definir a escala
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @DisplayName("Deve realizar a distribuição de investimentos corretamente (30/30/40)")
    void deveRealizarDistribuicaoInvestimento() {
        // Arrange
        Long usuarioId = 1L;
        BigDecimal valorTotal = new BigDecimal("1000.00");
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        when(repository.save(any(Lancamento.class))).thenAnswer(invocation -> {
            Lancamento entity = invocation.getArgument(0);
            if (entity.getId() == null) {
                entity.setId(1L);
            }
            return entity;
        });

        // Act
        service.realizarDistribuicaoInvestimento(usuarioId, valorTotal);

        // Assert
        ArgumentCaptor<Lancamento> captor = ArgumentCaptor.forClass(Lancamento.class);
        verify(repository, times(3)).save(captor.capture());

        List<Lancamento> salvos = captor.getAllValues();

        // Valida se os cálculos de 30/30/40 foram aplicados corretamente, forçando a
        // escala para 2 casas
        assertEquals(new BigDecimal("300.00"), salvos.get(0).getValor().setScale(2, RoundingMode.HALF_UP)); // Reserva
        assertEquals(new BigDecimal("300.00"), salvos.get(1).getValor().setScale(2, RoundingMode.HALF_UP)); // Dividendos
        assertEquals(new BigDecimal("400.00"), salvos.get(2).getValor().setScale(2, RoundingMode.HALF_UP)); // Day Trade

        verify(lucroRepository, times(1)).save(any());
    }
}