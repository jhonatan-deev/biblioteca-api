package com.biblioteca.biblioteca_api.service;

import com.biblioteca.biblioteca_api.dto.LivroDTO;
import com.biblioteca.biblioteca_api.dto.LivroRequestDTO;
import com.biblioteca.biblioteca_api.exception.BookNotFoundException;
import com.biblioteca.biblioteca_api.mapper.LivroMapper;
import com.biblioteca.biblioteca_api.model.Livro;
import com.biblioteca.biblioteca_api.repository.LivroRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    private LivroDTO toDTO(Livro livro){
        return new LivroDTO(
                livro.getId()
                , livro.getTitulo()
                , livro.getAutor()
                ,livro.getAno()
                ,livro.getAvaliacao()
        );
    }

    private Livro buscarLivroOuFalhar(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livro não encontrado. ID: " + id));
    }

    public LivroDTO create(LivroRequestDTO dto) {
        Livro livro = livroMapper.toEntity(dto);
        livro.setEmprestado(false);

        Livro saved = livroRepository.save(livro);
        return livroMapper.toDTO(saved);
    }


    public List<LivroDTO> searchAllBooks(){
        return livroRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public LivroDTO searchBookById(Long id){
        Livro livro = buscarLivroOuFalhar(id);
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

    public LivroDTO updateBookById(Long id, LivroDTO livroDTO){
        Livro livro = buscarLivroOuFalhar(id);
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setAno(livroDTO.getAnoPublicacao());
        livro.setAvaliacao(livroDTO.getAvaliacao());
        Livro livroAtualizado = livroRepository.save(livro);
        return toDTO(livroAtualizado);
    }

    public boolean deleteBookById(Long id){
        Livro livro = buscarLivroOuFalhar(id);
        if(livro != null){
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public LivroDTO emprestarLivroById(Long id){
        Livro livro = buscarLivroOuFalhar(id);
        if(livro.getEmprestado()){
            throw new IllegalStateException("Livro já está emprestado");
        }
        livro.setEmprestado(true);
        Livro livroEmprestado = livroRepository.save(livro);
        return toDTO(livroEmprestado);

    }

    public LivroDTO returnBookById(Long id){
        Livro livro = buscarLivroOuFalhar(id);
        if(!livro.getEmprestado()){
            throw new IllegalStateException("O livro já está disponível.");
        }
        livro.setEmprestado(false);
        Livro livroDevolvido = livroRepository.save(livro);
        return toDTO(livroDevolvido);
    }

    public List<LivroDTO> searchBookByTitulo(String titulo){
        List<Livro> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if(livros.isEmpty()){
            throw new BookNotFoundException("Livro não Encontrado.");
        }
        return livros.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<LivroDTO> searchBookByNotLoaned(){
        List<Livro> livros = livroRepository.findByEmprestadoFalse();
        if(livros.isEmpty()){
            throw new BookNotFoundException("Nenhum livro disponível encontrado");
        }
        return livros.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<LivroDTO> searchBookByLoaned(){
        List<Livro> livros = livroRepository.findByEmprestadoTrue();
        if(livros.isEmpty()){
            throw new BookNotFoundException("Nenhum livro emprestado encontrado");
        }
        return livros.stream()
                .map(this::toDTO)
                .toList();
    }

}
