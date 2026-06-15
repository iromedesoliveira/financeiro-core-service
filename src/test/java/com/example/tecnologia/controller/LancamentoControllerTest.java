package com.example.tecnologia.controller;

import com.example.tecnologia.dto.LancamentoDTO;
import com.example.tecnologia.service.LancamentoService;
import com.example.tecnologia.infra.security.TokenService;
import com.example.tecnologia.repository.UsuarioRepository;
import com.example.tecnologia.infra.security.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LancamentoController.class)
@Import(SecurityConfig.class)
class LancamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LancamentoService service;

    @MockitoBean
    private TokenService tokenService;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve retornar 201 quando criar um lançamento com sucesso")
    @WithMockUser
    void deveCriarLancamentoComSucesso() throws Exception {
        LancamentoDTO dto = new LancamentoDTO(null, "Almoço", LocalDate.now(), new BigDecimal("50.00"), 1L);
        LancamentoDTO salvo = new LancamentoDTO(1L, "Almoço", LocalDate.now(), new BigDecimal("50.00"), 1L);

        when(service.salvar(any())).thenReturn(salvo);

        mockMvc.perform(post("/lancamentos")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.descricao", is("Almoço")));
    }
}