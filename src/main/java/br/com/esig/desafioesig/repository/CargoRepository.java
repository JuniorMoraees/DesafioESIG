package br.com.esig.desafioesig.repository;

import br.com.esig.desafioesig.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
