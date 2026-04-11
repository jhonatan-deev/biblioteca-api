package com.biblioteca.biblioteca_api.dto;

public record LivroDTO(
        Long id,
        String titulo,
        String autor,
        Integer anoPublicacao,
        Double avaliacao
) {}