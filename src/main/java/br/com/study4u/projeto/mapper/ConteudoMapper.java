package br.com.study4u.projeto.mapper;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;

import br.com.study4u.projeto.dto.ConteudoDto;
import br.com.study4u.projeto.entity.ConteudoEntity;

@Configuration
public class ConteudoMapper {
	
	public ConteudoDto converterParaDto(ConteudoEntity entidade) {
		return new ConteudoDto(entidade);
	}
	
	public ConteudoEntity converterParaEntidade(ConteudoDto dto) {
		dto.setDataCriacao(LocalDateTime.now());
		return new ConteudoEntity(dto);
	}

}
