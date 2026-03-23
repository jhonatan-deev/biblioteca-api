package com.biblioteca.biblioteca_api.repository;

import com.biblioteca.biblioteca_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    List<Livro> findByEmprestadoFalse();
    List<Livro> findByEmprestadoTrue();
}
