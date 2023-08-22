package br.com.esig.desafioesig.repository;

import br.com.esig.desafioesig.domain.Salario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


public interface SalarioRepository extends JpaRepository<Salario, Integer> {

    @Modifying
    @Query("UPDATE Salario s SET s.salario = :salario WHERE s.pessoa.id = :pessoaId")
    void update(@Param("pessoaId") Long pessoaId, @Param("salario") Double salario);

    @Transactional
    @Modifying
    @Query("DELETE FROM Salario s WHERE s.pessoa.id = :pessoaId")
    void deleteByPessoaId(Long pessoaId);
}
