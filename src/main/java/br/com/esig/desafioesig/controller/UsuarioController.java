package br.com.esig.desafioesig.controller;

import br.com.esig.desafioesig.domain.Usuario;
import br.com.esig.desafioesig.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {
    private static final long serialVersionUID = -5992849924713890836L;

    @Autowired
    private UsuarioRepository repository;

    @Getter
    @Setter
    private String login;

    @Getter
    @Setter
    private String senha;

    public String login() {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            Usuario usuario = buscarUsuarioPeloLogin(login);

            if (usuario != null && usuario.getSenha().equals(senha)) {
                return "pessoa.com?faces-redirect=true";
            } else {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido", null));
                return null;
            }
        } catch (Exception ex) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro durante o login: " + ex.getMessage(), null));
            return null;
        }
    }

    private Usuario buscarUsuarioPeloLogin(String login) {
        return repository.findUsuarioByLogin(login);
    }
}
