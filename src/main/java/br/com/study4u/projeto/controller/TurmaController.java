package br.com.study4u.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.study4u.projeto.dto.TurmaDto;
import br.com.study4u.projeto.service.TurmaService;

@CrossOrigin
@RequestMapping("turmas")
@RestController()
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@PostMapping()
	public ResponseEntity<?> inserir(@RequestBody TurmaDto turmaDto, @RequestParam Long id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.inserir(turmaDto, id));
	}
	
	@PutMapping()
	public ResponseEntity<?> atualizar(@RequestBody TurmaDto turmaDto) {
		return ResponseEntity.status(HttpStatus.OK).body(turmaService.atualizar(turmaDto));
	}
	
	@GetMapping("/consultar-turma/{id}")
	public ResponseEntity<?> consultarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(turmaService.consultarPorId(id));
	}
	
	@GetMapping
	public ResponseEntity<?> listarTodos() {
		return ResponseEntity.ok().body(turmaService.listarTodos());
	}
	
	@GetMapping("/pesquisar-titulo")
	public ResponseEntity<?> pesquisarPorTitulo(@RequestParam String pesquisa) {
		return ResponseEntity.ok().body(turmaService.pesquisarPorTitulo(pesquisa));
	}
	
	@PutMapping("/{turmaId}/atribuir-usuario")
	public ResponseEntity<?> atribuirUsuario(@PathVariable("turmaId") Long turmaId, @RequestParam("usuarioId") Long usuarioId) {
		return ResponseEntity.ok().body(turmaService.atribuirUsuario(turmaId, usuarioId));
	}
	
	@PutMapping("/{turmaId}/remover-usuario")
	public ResponseEntity<?> removerUsuario(@PathVariable("turmaId") Long turmaId, @RequestParam("usuarioId") Long usuarioId) {
		turmaService.removerUsuarioDaTurma(turmaId, usuarioId);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> listarTurmasPorUsuario(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok().body(turmaService.listarTurmasPorUsuario(usuarioId));
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPorId(@PathVariable("id") Long id) {
        turmaService.remover(id);
        return ResponseEntity.ok().body(null);
    }
	
	

}
