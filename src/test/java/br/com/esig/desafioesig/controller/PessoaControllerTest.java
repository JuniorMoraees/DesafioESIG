package br.com.esig.desafioesig.controller;

import br.com.esig.desafioesig.domain.Pessoa;
import br.com.esig.desafioesig.domain.Salario;
import br.com.esig.desafioesig.repository.PessoaRepository;
import br.com.esig.desafioesig.repository.SalarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class PessoaControllerTest {

    PessoaRepository pessoaRepositoryMock = mock(PessoaRepository.class);
    SalarioRepository salarioRepositoryMock = mock(SalarioRepository.class);

    @InjectMocks
    private PessoaController pessoaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPessoas() {
        List<Pessoa> mockPessoas = new ArrayList<>();
        mockPessoas.add(new Pessoa());
        when(pessoaRepositoryMock.findAll()).thenReturn(mockPessoas);

        List<Pessoa> result = pessoaController.getPessoas();

        assertEquals(mockPessoas, result);
    }

    @Test
    public void testGravar() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste");

        Salario salario = new Salario();
        salario.setNome("Teste");
        salario.setSalario(1000.0);

        pessoa.setSalario(salario);

        when(pessoaRepositoryMock.save(any(Pessoa.class))).thenReturn(pessoa);

        when(salarioRepositoryMock.save(any(Salario.class))).thenReturn(salario);

        FacesContext mockFacesContext = mock(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(mockFacesContext);

        pessoaController.setPessoa(pessoa);
        pessoaController.gravar();

        verify(pessoaRepositoryMock).save(eq(pessoa));
        verify(salarioRepositoryMock).save(eq(salario));
    }

    @Test
    public void testAlterar() {

        Salario salario = new Salario();
        salario.setNome("Teste");
        salario.setSalario(1000.0);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Teste");
        pessoa.setSalario(salario);


        when(pessoaRepositoryMock.findById(anyLong())).thenReturn(Optional.of(pessoa));
        when(pessoaRepositoryMock.save(any(Pessoa.class))).thenReturn(pessoa);

        when(salarioRepositoryMock.save(any(Salario.class))).thenReturn(salario);

        FacesContext mockFacesContext = mock(FacesContext.class);
        when(FacesContext.getCurrentInstance()).thenReturn(mockFacesContext);

        pessoaController.setPessoa(pessoa);
        pessoaController.alterar();

        verify(pessoaRepositoryMock).save(eq(pessoa));
        verify(salarioRepositoryMock).save(eq(salario));
    }

        @Test
        public void testExcluir() {

            Salario salario = new Salario();
            salario.setId(1);
            salario.setNome("Teste");
            salario.setSalario(1000.0);

            Pessoa pessoa = new Pessoa();
            pessoa.setId(1L);
            pessoa.setNome("Teste");
            pessoa.setSalario(salario);

            when(pessoaRepositoryMock.findById(anyLong())).thenReturn(Optional.of(pessoa));

            FacesContext mockFacesContext = mock(FacesContext.class);
            when(FacesContext.getCurrentInstance()).thenReturn(mockFacesContext);

            salarioRepositoryMock.deleteById(salario.getId());
            pessoaController.setPessoa(pessoa);
            pessoaController.excluir();

            verify(pessoaRepositoryMock).delete(eq(pessoa));

            verify(mockFacesContext).addMessage(eq(null), any(FacesMessage.class));
        }
}
