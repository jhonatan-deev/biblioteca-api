package com.biblioteca.biblioteca_api.dto;

import jakarta.validation.constraints.NotBlank;


public record LivroRequestDTO(
        @NotBlank String titulo,
        String autor,
        Integer anoPublicacao,
        Double avaliacao
) {}