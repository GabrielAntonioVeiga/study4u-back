package br.com.study4u.projeto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.study4u.projeto.dto.ConteudoDto;
import br.com.study4u.projeto.dto.TurmaDto;
import br.com.study4u.projeto.entity.ConteudoEntity;
import br.com.study4u.projeto.entity.TurmaEntity;
import br.com.study4u.projeto.mapper.ConteudoMapper;
import br.com.study4u.projeto.mapper.TurmaMapper;
import br.com.study4u.projeto.repository.ConteudoRepository;

@Service
public class ConteudoService {
	
	@Autowired
	private ConteudoRepository conteudoRepository;
	
	@Autowired
	private ConteudoMapper conteudoMapper;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private TurmaMapper turmaMapper;
	
	public ConteudoDto inserir(ConteudoDto conteudoDto, Long idTurma) {
		
		TurmaDto turmaDto = turmaService.consultarPorId(idTurma);
		TurmaEntity turma = turmaMapper.converterParaEntidade(turmaDto);
		
		
		ConteudoEntity entidade = conteudoMapper.converterParaEntidade(conteudoDto);
		
		entidade.setTurma(turma);
		
		conteudoRepository.save(entidade);
		
		
		return conteudoMapper.converterParaDto(entidade);
	}
	
	public List<ConteudoDto> listarTodosPorTurma(Long idTurma) {
		TurmaDto turmaDto = turmaService.consultarPorId(idTurma);
		TurmaEntity turma = turmaMapper.converterParaEntidade(turmaDto);
		
		List<ConteudoEntity> conteudos = conteudoRepository.findAllByTurma(turma); 
		return conteudos.stream().map(conteudoMapper::converterParaDto).collect(Collectors.toList());
	}
	
	public void remover(Long idTurma) {
		conteudoRepository.delete(conteudoRepository.findById(idTurma).orElseThrow(() -> new Error("Conteúdo não encontrado para o id " + idTurma)));
	}
	

}