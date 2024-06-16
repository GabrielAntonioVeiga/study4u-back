package br.com.study4u.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.study4u.projeto.entity.TurmaEntity;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaEntity, Long>{
	
//	Hibernate
//	@Query("SELECT t FROM TurmaEntity t WHERE LOWER(t.titulo) LIKE %:pesquisa%")
//	List<TurmaEntity> findByTituloContainingIgnoreCase(String pesquisa);

//	Native query
//	@Query(value = "SELECT * FROM turma WHERE LOWER(titulo) LIKE %:pesquisa%", nativeQuery = true)
//	List<TurmaEntity> findByTituloContainingIgnoreCase(String pesquisa);
	
	List<TurmaEntity> findByTituloContainingIgnoreCase(String pesquisa);
	
    List<TurmaEntity> findAllByUsuarios_Id(Long usuarioId);

}
