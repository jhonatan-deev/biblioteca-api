package com.biblioteca.biblioteca_api.dto;

public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private Integer anoPublicacao;
    private Double avaliacao;

    // Construtor com todos os parâmetros
    public LivroDTO(Long id, String titulo, String autor, Integer anoPublicacao, Double avaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.avaliacao = avaliacao;
    }


    public LivroDTO() {
    }

    public Long getId() {
        return id;
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