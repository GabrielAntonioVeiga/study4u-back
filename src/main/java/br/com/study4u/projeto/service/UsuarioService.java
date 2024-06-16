package br.com.study4u.projeto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.study4u.projeto.dto.UsuarioDto;
import br.com.study4u.projeto.entity.UsuarioEntity;
import br.com.study4u.projeto.mapper.UsuarioMapper;
import br.com.study4u.projeto.repository.UsuarioRepository;
import exception.RecordNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioMapper usuarioMapper;

	public UsuarioDto inserir (UsuarioDto usuarioDto) {
		UsuarioEntity entidade = usuarioMapper.converterParaEntidade(usuarioDto);
		
		boolean emailExistente = usuarioRepository.existsByEmail(entidade.getEmail());
		if(emailExistente) {
			throw new Error("Esse emal já existe");
		}
		
		entidade.setSenha(passwordEncoder.encode(entidade.getSenha()));
		usuarioRepository.save(entidade);
		
		return usuarioDto;
	}
	
	public UsuarioDto atualizar (UsuarioDto usuarioDto) {
		UsuarioEntity entidade = usuarioMapper.converterParaEntidade(usuarioDto);
		
		boolean emailExistente = usuarioRepository.existsByEmailAndIdNot(entidade.getEmail(), entidade.getId());
		if(emailExistente) {
			throw new Error("Essa email já existe");
		}
		
		entidade.setSenha(passwordEncoder.encode(entidade.getSenha()));
		usuarioRepository.save(entidade);
		
		return usuarioDto;
	}
	
	public UsuarioDto consultarPorId(Long id) {
	    return usuarioRepository.findById(id)
	            .map(usuarioMapper::converterParaDto)
	            .orElseThrow(() -> new RecordNotFoundException("Usuário", id));
	}

	
	public List<UsuarioDto> listarTodos() {
		List<UsuarioEntity> usuarios = usuarioRepository.findAll(); 
		return usuarios.stream().map(usuarioMapper::converterParaDto).collect(Collectors.toList());
	}
	
	public void remover(Long id) {
		usuarioRepository.delete(usuarioRepository.findById(id).orElseThrow(() -> new Error("Usuário não encontrado para o id " + id)));
	}

	
}
