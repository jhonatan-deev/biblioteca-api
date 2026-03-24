package com.biblioteca.biblioteca_api.service;

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

    public List<Livro> searchAllBooks(){
        return livroRepository.findAll();
    }

    public Livro searchBookById(Long id){
       return livroRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Livro não Encontrado."));
    }
}
