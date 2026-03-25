package com.biblioteca.biblioteca_api.dto;

import com.biblioteca.biblioteca_api.model.Livro;

public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;

    public LivroDTO() {}
    public LivroDTO(Long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

}
