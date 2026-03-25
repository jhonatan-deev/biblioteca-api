package com.biblioteca.biblioteca_api.service;

import com.biblioteca.biblioteca_api.dto.LivroDTO;
import com.biblioteca.biblioteca_api.exception.BookNotFoundException;
import com.biblioteca.biblioteca_api.exception.ErrorWhenSearchingForBookInApiException;
import com.biblioteca.biblioteca_api.model.Livro;
import com.biblioteca.biblioteca_api.repository.LivroRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
    private LivroDTO toDTO(Livro livro){
        return new LivroDTO(
                livro.getId()
                , livro.getTitulo()
                , livro.getAutor()
        );
    }

    public List<LivroDTO> searchAllBooks(){
        return livroRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public LivroDTO searchBookById(Long id){
        Livro livro = livroRepository.findById(id)
               .orElseThrow(() -> new BookNotFoundException("Livro não Encontrado."));
        return toDTO(livro);
    }

    public List<LivroDTO> searchBookByAutor(String autor){
        List<Livro> livros = livroRepository.findByAutorContainingIgnoreCase(autor);
        if(livros.isEmpty()){
            throw new BookNotFoundException("Nenhum livro encontrado para o autor: " + autor);
        }
        return livros.stream()
                .map(this::toDTO)
                .toList();
    }
}
