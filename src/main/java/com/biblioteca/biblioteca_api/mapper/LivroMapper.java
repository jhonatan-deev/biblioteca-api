package com.biblioteca.biblioteca_api.mapper;

import com.biblioteca.biblioteca_api.dto.LivroDTO;
import com.biblioteca.biblioteca_api.dto.LivroRequestDTO;
import com.biblioteca.biblioteca_api.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequestDTO dto){
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setAno(dto.getAnoPublicacao());
        livro.setAvaliacao(dto.getAvaliacao());
        return livro;
    }

    public LivroDTO toDTO(Livro livro){
        return new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAno(),
                livro.getAvaliacao()
        );
    }

    public void updateEntityFromDTO(LivroRequestDTO dto, Livro livro){
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setAno(dto.getAnoPublicacao());
        livro.setAvaliacao(dto.getAvaliacao());
    }
}
