package com.biblioteca.biblioteca_api.dto;

import jakarta.validation.constraints.NotBlank;

public class LivroRequestDTO {
    @NotBlank
    private String titulo;
    private String autor;
    private Integer anoPublicacao;
    private Double avaliacao;


    public LivroRequestDTO(String titulo, String autor, Integer anoPublicacao, Double avaliacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.avaliacao = avaliacao;
    }

    public LivroRequestDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}