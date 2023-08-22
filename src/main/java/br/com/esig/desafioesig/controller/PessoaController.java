package br.com.esig.desafioesig.controller;

import br.com.esig.desafioesig.domain.Pessoa;
import br.com.esig.desafioesig.domain.Salario;
import br.com.esig.desafioesig.repository.PessoaRepository;
import br.com.esig.desafioesig.repository.SalarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "pessoaController")
@ViewScoped
public class PessoaController implements Serializable {
    private static final long serialVersionUID = -5992849924713890836L;

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private SalarioRepository salarioRepository;

    @Getter
    @Setter
    private Pessoa pessoa = new Pessoa();

    @Getter
    @Setter
    private List<Pessoa> pessoas = new ArrayList<>();

    public List<Pessoa> getPessoas() {
        try {
            this.pessoas = repository.findAll();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return pessoas;
    }


    @Transactional
    public void gravar(){
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            repository.save(this.pessoa);

            Double salario = pessoa.calcularSalario();
            Salario sal = new Salario();
            sal.setPessoa(this.pessoa);
            sal.setNome(this.pessoa.getNome());
            sal.setSalario(salario);

            salarioRepository.save(sal);

            this.pessoa = new Pessoa();
            fc.addMessage(null, new FacesMessage("Cadastrado com Sucesso!"));
        }catch (Exception ex) {
            fc.addMessage(null, new FacesMessage("error: Verifique os dados digitados"));
        }
    }

    @Transactional
    public void alterar(){
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            Pessoa existPessoa = repository.findById(this.pessoa.getId()).orElse(null);
            if (existPessoa != null) {
                repository.save(this.pessoa);

                Double salario = pessoa.calcularSalario();
                Salario sal = new Salario();
                sal.setPessoa(this.pessoa);
                sal.setNome(this.pessoa.getNome());
                sal.setSalario(salario);

                salarioRepository.update(pessoa.getId(), sal.getSalario());

                this.pessoa = new Pessoa();
                fc.addMessage(null, new FacesMessage("Alterado com Sucesso!"));
            } else {
                fc.addMessage(null, new FacesMessage("Pessoa não encontrada."));
            }
        }catch(Exception ex){
                fc.addMessage(null, new FacesMessage("error: " + ex.getMessage()));
            }
        }



    public void excluir() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            Long pessoaId = this.pessoa.getId();
            salarioRepository.deleteByPessoaId(pessoaId);
            repository.delete(this.pessoa);
            this.pessoa = new Pessoa();
            fc.addMessage(null, new FacesMessage("Excluído com Sucesso!"));
        }catch (Exception ex) {
            fc.addMessage(null, new FacesMessage("error: " +ex.getMessage()));
        }
    }


}
