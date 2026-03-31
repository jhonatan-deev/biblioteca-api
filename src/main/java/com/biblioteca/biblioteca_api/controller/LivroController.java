package com.biblioteca.biblioteca_api.controller;

import com.biblioteca.biblioteca_api.dto.LivroDTO;
import com.biblioteca.biblioteca_api.dto.LivroRequestDTO;
import com.biblioteca.biblioteca_api.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroDTO criar(@RequestBody @Valid LivroRequestDTO livroDTO){
        return service.create(livroDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(service.deleteBookById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @RequestBody LivroRequestDTO dto){
        LivroDTO atualizado = service.updateBookById(id, dto);
        if(atualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/titulo")
    public List<LivroDTO> buscarPorTitulo(@RequestParam String titulo){
        List<LivroDTO> livros = service.searchBookByTitulo(titulo);
        return livros;
    }

    @PatchMapping("/{id}/emprestar")
    public LivroDTO emprestar(@PathVariable Long id){
        return service.emprestarLivroById(id);
    }

    @PatchMapping("/{id}/devolver")
    public LivroDTO devolver(@PathVariable Long id){
        return service.returnBookById(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id){
        LivroDTO livro = service.searchBookById(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping
    public List<LivroDTO> buscarTodos(){
        return service.searchAllBooks();
    }

    @GetMapping("/autor")
    public List<LivroDTO> buscarAutores(@RequestParam String autor){
        return service.searchBookByAutor(autor);
    }

    @GetMapping("/emprestados")
    public List<LivroDTO> buscarEmprestados(){
        return service.searchBookByLoaned();
    }

    @GetMapping("/disponiveis")
    public List<LivroDTO> buscarDisponiveis(){
        return service.searchBookByNotLoaned();
    }
}
