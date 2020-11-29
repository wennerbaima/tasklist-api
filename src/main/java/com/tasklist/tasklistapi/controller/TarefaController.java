package com.tasklist.tasklistapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.tasklist.tasklistapi.model.Tarefa;
import com.tasklist.tasklistapi.repository.TarefaRepository;

/** 
 * CLASSE RESPONSÁVEL POR CONTROLAR AS REQUISIÇÕES REFERENTES À ENTIDADE TAREFA
 *
 * @author Wenner
 */

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

	@Autowired
	private TarefaRepository repository;
	
	@GetMapping
	public List<Tarefa> findAll() {
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Tarefa> findById(
			@PathVariable Long id,
			@RequestParam(name = "sort", required = false, defaultValue = "id!asc") List<String> sort) {
		return repository.findById(id)
				.map(result -> ResponseEntity.ok().body(result))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Tarefa create(@RequestBody Tarefa tarefa) {
		return repository.save(tarefa);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
		return repository.findById(id)
		        .map(result -> {
		        	result.setTitulo(tarefa.getTitulo());
		        	result.setDescricao(tarefa.getDescricao());
		        	result.setConcluido(tarefa.getConcluido());
		        	result.setDataCadastro(tarefa.getDataCadastro());
		        	result.setDataEdicao(tarefa.getDataEdicao());
		            Tarefa updated = repository.save(result);
		            return ResponseEntity.ok().body(updated);
		        }).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(value = "{id}")
	  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
	    return repository.findById(id)
	        .map(result -> {
	            repository.deleteById(id);
	            return ResponseEntity.ok().build();
	        }).orElse(ResponseEntity.notFound().build());
	  }
}
