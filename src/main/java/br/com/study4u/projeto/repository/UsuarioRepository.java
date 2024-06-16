package br.com.study4u.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.study4u.projeto.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{
	
	Optional<UsuarioEntity> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	boolean existsByEmailAndIdNot(String email, Long id);
	
}
