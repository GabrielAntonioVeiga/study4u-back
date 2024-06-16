package br.com.study4u.projeto.mapper;

import org.springframework.context.annotation.Configuration;

import br.com.study4u.projeto.dto.UsuarioDto;
import br.com.study4u.projeto.entity.UsuarioEntity;

@Configuration
public class UsuarioMapper {

	public UsuarioDto converterParaDto(UsuarioEntity entidade) {
		return new UsuarioDto(entidade);
	}
	
	public UsuarioEntity converterParaEntidade(UsuarioDto dto) {
		return new UsuarioEntity(dto);
	}
}
