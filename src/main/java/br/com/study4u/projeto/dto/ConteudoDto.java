package br.com.study4u.projeto.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.study4u.projeto.entity.ConteudoEntity;
import br.com.study4u.projeto.entity.TurmaEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConteudoDto {
	
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private LocalDateTime dataCriacao;

	public ConteudoDto() {
		super();
	}
	
	public ConteudoDto(ConteudoEntity entidade) {
		BeanUtils.copyProperties(entidade, this);
	}

}
