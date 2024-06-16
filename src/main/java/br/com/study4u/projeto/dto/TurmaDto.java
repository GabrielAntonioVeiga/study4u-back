package br.com.study4u.projeto.dto;

import org.springframework.beans.BeanUtils;

import br.com.study4u.projeto.entity.TurmaEntity;
import br.com.study4u.projeto.entity.UsuarioEntity;
import lombok.Data;

@Data
public class TurmaDto {
	
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private UsuarioDto criador;
	
	public TurmaDto() {
		super();
	}

	public TurmaDto(TurmaEntity entidade) {
		BeanUtils.copyProperties(entidade, this);
		this.criador = new UsuarioDto();
        UsuarioEntity criadorEntity = entidade.getCriador();
        this.criador.setId(criadorEntity.getId());
        this.criador.setNome(criadorEntity.getNome());
	}

}
