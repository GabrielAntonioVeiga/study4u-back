package br.com.study4u.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.study4u.projeto.entity.ConteudoEntity;
import br.com.study4u.projeto.entity.TurmaEntity;

@Repository
public interface ConteudoRepository extends JpaRepository<ConteudoEntity, Long>{
	
	List<ConteudoEntity> findAllByTurma(TurmaEntity turma);

}
