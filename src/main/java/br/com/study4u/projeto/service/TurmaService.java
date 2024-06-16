package br.com.study4u.projeto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.study4u.projeto.dto.TurmaDto;
import br.com.study4u.projeto.dto.UsuarioDto;
import br.com.study4u.projeto.entity.TurmaEntity;
import br.com.study4u.projeto.entity.UsuarioEntity;
import br.com.study4u.projeto.mapper.TurmaMapper;
import br.com.study4u.projeto.mapper.UsuarioMapper;
import br.com.study4u.projeto.repository.TurmaRepository;
import br.com.study4u.projeto.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TurmaService {
	
	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TurmaMapper turmaMapper;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public TurmaDto inserir (TurmaDto turmaDto, Long id ) {
		TurmaEntity entidade = turmaMapper.converterParaEntidade(turmaDto);
		
		UsuarioDto usuarioDto = usuarioService.consultarPorId(id);
		UsuarioEntity usuario = usuarioMapper.converterParaEntidade(usuarioDto);
		
		entidade.setCriador(usuario);
		
		TurmaEntity turmaCriada =  turmaRepository.save(entidade);
		turmaCriada.atribuirUsuario(usuario);
		
		
		TurmaEntity turmaAtribuida = turmaRepository.save(turmaCriada);
		
		return turmaMapper.converterParaDto(turmaAtribuida);
	}
	
	public TurmaDto atualizar (TurmaDto turmaDto) {
		TurmaEntity entidade = turmaMapper.converterParaEntidade(turmaDto);
		
		turmaRepository.save(entidade);
		
		return turmaDto;
	}
	
	public TurmaDto consultarPorId(Long id) {
		return turmaRepository.findById(id).map(turmaMapper::converterParaDto)
                .orElseThrow(() -> new Error("Turma não encontrado para o id " + id));

	}
	
	public List<TurmaDto> listarTodos() {
		List<TurmaEntity> turmas = turmaRepository.findAll(); 
		return turmas.stream().map(turmaMapper::converterParaDto).collect(Collectors.toList());
	}
	
	public List<TurmaDto> pesquisarPorTitulo(String titulo) {
		System.out.println(titulo);
			List<TurmaEntity> turmas = turmaRepository.findByTituloContainingIgnoreCase(titulo);
			return turmas.stream().map(turmaMapper::converterParaDto).collect(Collectors.toList());
	}
	
	public void remover(Long id) {
		TurmaEntity turma = turmaRepository.findById(id).orElseThrow(() -> new Error("Turma não encontrado para o id " + id));
		
		List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>(turma.getUsuarios());
		
		for (UsuarioEntity usuario : usuarios) {
			usuario.removerTurma(turma);
		}
		
		turmaRepository.delete(turma);
	}
	
	public TurmaDto atribuirUsuario(Long turmaId, Long usuarioId) {
		TurmaEntity turma = turmaRepository.findById(turmaId).orElseThrow(() -> new Error("Turma não encontrado para o id " + turmaId));
		
		UsuarioDto usuarioDto = usuarioService.consultarPorId(usuarioId);
		UsuarioEntity usuario = usuarioMapper.converterParaEntidade(usuarioDto);
		turma.atribuirUsuario(usuario);
		turmaRepository.save(turma);
		
		return turmaMapper.converterParaDto(turma);
	}
	
	 public List<TurmaDto> listarTurmasPorUsuario(Long usuarioId) {
	        List<TurmaEntity> turmas = turmaRepository.findAllByUsuarios_Id(usuarioId);
	        return turmas.stream().map(turmaMapper::converterParaDto).collect(Collectors.toList());
	    
	 }
	 
	 public void removerUsuarioDaTurma(Long turmaId, Long usuarioId) {
		 TurmaEntity turma = turmaRepository.findById(turmaId)
		            .orElseThrow(() -> new RuntimeException("Turma not found"));
		 
		 UsuarioDto usuarioDto = usuarioService.consultarPorId(usuarioId);
		 UsuarioEntity usuario = usuarioMapper.converterParaEntidade(usuarioDto);
		 
		 turma.removerUsuario(usuario);
		 turmaRepository.save(turma);
	 }
	
}
