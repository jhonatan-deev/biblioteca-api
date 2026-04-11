package com.biblioteca.biblioteca_api;

import com.biblioteca.biblioteca_api.dto.LivroDTO;
import com.biblioteca.biblioteca_api.dto.LivroRequestDTO;
import com.biblioteca.biblioteca_api.exception.BookNotFoundException;
import com.biblioteca.biblioteca_api.mapper.LivroMapper;
import com.biblioteca.biblioteca_api.model.Livro;
import com.biblioteca.biblioteca_api.repository.LivroRepository;
import com.biblioteca.biblioteca_api.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private LivroMapper livroMapper;

    @InjectMocks
    private LivroService livroService;

    private Livro livro;
    private LivroDTO livroDTO;
    private LivroRequestDTO livroRequestDTO;

    @BeforeEach
        void setUp() {
            livro = new Livro();
            livro.setId(1L);
            livro.setTitulo("Dom Casmurro");
            livro.setAutor("Machado de Assis");
            livro.setAno(1899);
            livro.setAvaliacao(5.0);
            livro.setEmprestado(false);

            livroDTO = new LivroDTO(
                    1L,
                    "Dom Casmurro",
                    "Machado de Assis",
                    1899,
                    5.0
            );

            livroRequestDTO = new LivroRequestDTO(
                    "Dom Casmurro",
                    "Machado de Assis",
                    1899,
                    5.0
            );
        }


    @Test
    @DisplayName("Deve criar um livro com sucesso")
    void deveCriarLivroComSucesso() {
        when(livroMapper.toEntity(livroRequestDTO)).thenReturn(livro);
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);
        when(livroMapper.toDTO(livro)).thenReturn(livroDTO);

        LivroDTO resultado = livroService.create(livroRequestDTO);

        assertThat(resultado).isNotNull();
        assertThat(resultado.id()).isEqualTo(1L);
        assertThat(resultado.titulo()).isEqualTo("Dom Casmurro");
        assertThat(resultado.autor()).isEqualTo("Machado de Assis");

        verify(livroMapper, times(1)).toEntity(livroRequestDTO);
        verify(livroRepository, times(1)).save(livro);
        verify(livroMapper, times(1)).toDTO(livro);
    }

    @Test
    @DisplayName("Deve buscar todos os livros com sucesso")
    void deveBuscarTodosOsLivros() {
        when(livroRepository.findAll()).thenReturn(List.of(livro));
        // toDTO não é chamado neste fluxo, então não precisa de stub

        List<LivroDTO> resultado = livroService.searchAllBooks();

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).titulo()).isEqualTo("Dom Casmurro");
        verify(livroRepository, times(1)).findAll();
    }
    @Test
    @DisplayName("DEBUG - Verificar buscarLivroOuFalhar")
    void debugVerificarBuscarLivroOuFalhar() {
        // Configurar o mock
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        // Chamar o método público que usa o buscarLivroOuFalhar
        LivroDTO resultado = livroService.searchBookById(1L);

        System.out.println("Resultado: " + resultado);
        System.out.println("Resultado ID: " + (resultado != null ? resultado.id() : "null"));

        assertThat(resultado).isNotNull();
    }
    @Test
    @DisplayName("Deve buscar livro por ID com sucesso")
    void deveBuscarLivroPorIdComSucesso() {
        // ARRANGE
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        // ACT
        LivroDTO resultado = livroService.searchBookById(1L);

        // ASSERT
        assertThat(resultado).isNotNull();
        assertThat(resultado.id()).isEqualTo(1L);
        assertThat(resultado.titulo()).isEqualTo("Dom Casmurro");
        assertThat(resultado.autor()).isEqualTo("Machado de Assis");
        assertThat(resultado.anoPublicacao()).isEqualTo(1899);
        assertThat(resultado.avaliacao()).isEqualTo(5.0);

        verify(livroRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando livro não for encontrado por ID")
    void deveLancarExcecaoQuandoLivroNaoEncontradoPorId() {
        when(livroRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> livroService.searchBookById(999L))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("Livro não encontrado. ID: 999");

        verify(livroRepository, times(1)).findById(999L);
        verify(livroMapper, never()).toDTO(any());
    }

    @Test
    @DisplayName("Deve emprestar livro com sucesso")
    void deveEmprestarLivroComSucesso() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);
        // toDTO não é chamado, o método retorna o DTO diretamente

        LivroDTO resultado = livroService.emprestarLivroById(1L);

        assertThat(resultado).isNotNull();
        assertThat(livro.getEmprestado()).isTrue();

        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar emprestar livro já emprestado")
    void deveLancarExcecaoAoTentarEmprestarLivroJaEmprestado() {
        livro.setEmprestado(true);
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        assertThatThrownBy(() -> livroService.emprestarLivroById(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Livro já está emprestado");

        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve devolver livro com sucesso")
    void deveDevolverLivroComSucesso() {
        livro.setEmprestado(true);
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);
        // toDTO não é chamado, o método retorna o DTO diretamente

        LivroDTO resultado = livroService.returnBookById(1L);

        assertThat(resultado).isNotNull();
        assertThat(livro.getEmprestado()).isFalse();

        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar devolver livro já disponível")
    void deveLancarExcecaoAoTentarDevolverLivroJaDisponivel() {
        livro.setEmprestado(false);
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        assertThatThrownBy(() -> livroService.returnBookById(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("O livro já está disponível");

        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar livro com sucesso")
    void deveDeletarLivroComSucesso() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        doNothing().when(livroRepository).deleteById(1L);

        boolean resultado = livroService.deleteBookById(1L);

        assertThat(resultado).isTrue();
        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando livro não existe ao deletar")
    void deveLancarExcecaoQuandoLivroNaoExisteAoDeletar() {
        when(livroRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> livroService.deleteBookById(999L))
                .isInstanceOf(BookNotFoundException.class)
                .hasMessageContaining("Livro não encontrado. ID: 999");

        verify(livroRepository, times(1)).findById(999L);
        verify(livroRepository, never()).deleteById(anyLong());
    }
}