package br.com.study4u.projeto.dto;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.study4u.projeto.entity.UsuarioEntity;
import lombok.Data;

@Data
public class UsuarioDto {
	
	private Long id;
	
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	private String email;
	
	@JsonInclude(Include.NON_NULL)
	private String senha;
	
	public UsuarioDto() {
		super();
	}
	
	public UsuarioDto(UsuarioEntity entidade) {
		BeanUtils.copyProperties(entidade, this);
	}
	
}
