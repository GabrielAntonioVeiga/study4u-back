package br.com.study4u.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.study4u.projeto.dto.UsuarioDto;
import br.com.study4u.projeto.service.UsuarioService;
import exception.RecordNotFoundException;

@CrossOrigin
@RequestMapping("usuarios")
@RestController()
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping()
	public ResponseEntity<?> inserir(@RequestBody UsuarioDto usuarioDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.inserir(usuarioDto));
	}
	
	@PutMapping()
	public ResponseEntity<?> atualizar(@RequestBody UsuarioDto usuarioDto) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizar(usuarioDto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(usuarioService.consultarPorId(id));
	}
	
	@GetMapping
	public ResponseEntity<?> listarTodos() {
		return ResponseEntity.ok().body(usuarioService.listarTodos());
	}
	
}
