package br.ufes.gestaocontatos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.gestaocontatos.model.dto.ContatoDTO;
import br.ufes.gestaocontatos.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoService contatoService;

	@PostMapping(consumes = { "application/json" })
	public ResponseEntity<Object> insert(@RequestBody ContatoDTO contato) {
		try {
			return new ResponseEntity<Object>(contatoService.insert(contato), HttpStatus.CREATED);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(consumes = { "application/json" })
	public ResponseEntity<Object> update(@RequestBody ContatoDTO contato) {
		try {
			return new ResponseEntity<Object>(contatoService.update(contato), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try {
			contatoService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(contatoService.getById(id), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public ResponseEntity<Object> getAll() {
		try {
			return new ResponseEntity<Object>(contatoService.getAll(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
