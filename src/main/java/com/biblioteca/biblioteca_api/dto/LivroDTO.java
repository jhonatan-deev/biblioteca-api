package com.biblioteca.biblioteca_api.dto;

public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private Integer ano_publicacao;

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
    public Integer getAno_publicacao() {
        return ano_publicacao;
    }

}
