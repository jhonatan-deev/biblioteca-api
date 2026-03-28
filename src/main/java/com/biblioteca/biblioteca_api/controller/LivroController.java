package com.biblioteca.biblioteca_api.controller;

import com.biblioteca.biblioteca_api.dto.LivroDTO;
import com.biblioteca.biblioteca_api.service.LivroService;
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

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id){
       LivroDTO livro = service.searchBookById(id);
       return ResponseEntity.ok(livro);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroDTO criar(@RequestBody LivroDTO livroDTO){
        return service.createBook(livroDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(service.deleteBookById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @RequestBody LivroDTO livroDTO){
        LivroDTO atualizado = service.updateBookById(id, livroDTO);
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

    @PostMapping("/{id}/emprestar")
    public LivroDTO emprestar(@PathVariable Long id){
        return service.emprestarLivroById(id);
    }

    @PostMapping("/{id}/devolver")
    public LivroDTO devolver(@PathVariable Long id){
        return service.returnBookById(id);
    }

    @GetMapping
    public List<LivroDTO> buscarTodos(){
        return service.searchAllBooks();
    }

    @GetMapping("/autor")
    public List<LivroDTO> buscarAutores(@RequestParam String autor){
        return service.searchBookByAutor(autor);
    }

    @GetMapping("/avaliados")
    public List<LivroDTO> buscarAvaliados(){
        return service.searchBookByAvailable();
    }





}
