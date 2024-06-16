package br.com.study4u.projeto.mapper;

import org.springframework.context.annotation.Configuration;

import br.com.study4u.projeto.dto.TurmaDto;
import br.com.study4u.projeto.entity.TurmaEntity;

@Configuration
public class TurmaMapper {
	
	public TurmaDto converterParaDto(TurmaEntity entidade) {
		return new TurmaDto(entidade);
	}
	
	public TurmaEntity converterParaEntidade(TurmaDto dto) {
		return new TurmaEntity(dto);
	}

}
