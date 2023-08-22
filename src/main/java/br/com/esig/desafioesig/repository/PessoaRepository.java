package br.com.esig.desafioesig.repository;

import br.com.esig.desafioesig.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
