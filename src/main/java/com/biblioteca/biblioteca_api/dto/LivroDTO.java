package com.biblioteca.biblioteca_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;

    private Integer anoPublicacao;
    private Double avaliacao;

    public LivroDTO() {}

    public LivroDTO(Long id, String titulo, String autor, Integer anoPublicacao, Double avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.avaliacao = avaliacao;
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

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }
}