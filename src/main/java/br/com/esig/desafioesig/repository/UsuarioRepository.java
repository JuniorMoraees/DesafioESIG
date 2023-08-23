package br.com.esig.desafioesig.repository;

import br.com.esig.desafioesig.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByLogin(String login);
}
