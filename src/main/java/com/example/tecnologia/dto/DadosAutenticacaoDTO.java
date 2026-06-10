package com.example.tecnologia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoDTO(
        @NotBlank(message = "O e-mail é obrigatório") @Email(message = "Formato de e-mail inválido") String email,

        @NotBlank(message = "A senha é obrigatória") String senha) {
}