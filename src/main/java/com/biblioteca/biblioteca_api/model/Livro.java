package com.biblioteca.biblioteca_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String titulo;
    @NotBlank
    @Column(nullable = false)
    private String autor;
    @NotNull
    @Column(nullable = false, name = "ano_publicacao")
    private Integer anoPublicacao;
    @Column(nullable = false)
    private Boolean emprestado;
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    @Column(nullable = false)
    private Double avaliacao;

    public Livro(){}
    public Livro(String titulo, String autor, Integer ano, Double avaliacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = ano;
        this.emprestado = false;
        this.avaliacao = avaliacao;
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
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

    public Integer getAno() {
        return anoPublicacao;
    }

    public Boolean getEmprestado() {
        return emprestado;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAno(Integer ano) {
        this.anoPublicacao = ano;
    }

    public void setEmprestado(Boolean emprestado) {
        this.emprestado = emprestado;
    }


    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano=" + anoPublicacao +
                ", emprestado=" + emprestado +
                '}';
    }
}
