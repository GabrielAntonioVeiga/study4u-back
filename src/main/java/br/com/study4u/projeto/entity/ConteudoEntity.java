package br.com.study4u.projeto.entity;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.study4u.projeto.dto.ConteudoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "conteudo")
public class ConteudoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 45)
	private String titulo;
	
	@NotBlank
	@Size(min = 3, max = 200)
	private String descricao;
	
	@NotNull
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	@JoinColumn(name = "turma_id")
	@JsonIgnore
	private TurmaEntity turma;
	
	public ConteudoEntity() {
		super();
	}
	
	public ConteudoEntity(ConteudoDto dto) {
		BeanUtils.copyProperties(dto, this);
	}
	
}
