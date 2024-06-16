package br.com.study4u.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.study4u.projeto.dto.ConteudoDto;
import br.com.study4u.projeto.service.ConteudoService;

@CrossOrigin
@RequestMapping("conteudos")
@RestController()
public class ConteudoController {
	
	@Autowired
	private ConteudoService conteudoService;
	
	@PostMapping()
	public ResponseEntity<?> inserir(@RequestBody ConteudoDto conteudoDto, @RequestParam Long idTurma) {
		return ResponseEntity.status(HttpStatus.CREATED).body(conteudoService.inserir(conteudoDto, idTurma));
	}
	
//	@PutMapping()
//	public ResponseEntity<?> atualizar(@RequestBody ConteudoDto conteudoDto) {
//		return ResponseEntity.status(HttpStatus.OK).body(conteudoService.atualizar(conteudoDto));
//	}
//	
	@GetMapping("/turma/{idTurma}")
	public ResponseEntity<?> listarTodosPorTurma(@PathVariable("idTurma") Long idTurma) {
		System.out.println(idTurma);
		return ResponseEntity.ok().body(conteudoService.listarTodosPorTurma(idTurma));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id) {
		conteudoService.remover(id);
		return ResponseEntity.ok().body(null);
	}
	
}
