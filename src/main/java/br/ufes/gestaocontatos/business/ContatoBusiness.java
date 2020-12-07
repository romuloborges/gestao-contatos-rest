package br.ufes.gestaocontatos.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.gestaocontatos.dao.impl.ContatoDAO;
import br.ufes.gestaocontatos.model.Contato;

@Service
public class ContatoBusiness {
	
	@Autowired
	private ContatoDAO contatoDAO;
	
	public Contato insert(Contato contato) throws Exception {
		validateInsert(contato);
		
		return contatoDAO.insert(contato);
	}
	
	public Contato update(Contato contato) throws Exception {
		validateUpdate(contato);
		
		return contatoDAO.update(contato);
	}
	
	public void delete(Long id) throws Exception {
		if (id == null) {
			throw new Exception("ID passado é inválido");
		}
		
		contatoDAO.delete(id);
	}
	
	public Contato getById(Long id) throws Exception {
		if (id == null) {
			throw new Exception("ID passado é inválido");
		}
		
		return contatoDAO.getById(id);
	}
	
	public List<Contato> getAll() throws Exception {
		return contatoDAO.getAll();
	}
	
	private void validateInsert(Contato contato) throws Exception {
		if (contato == null) {
			throw new Exception("Contato fornecido é inválido");
		}
		
		if (contato.getId() != null) {
			throw new Exception("Contato para inserção não pode conter o atributo 'id' preenchido");
		}
		
		if (contato.getNome() == null || contato.getNome().isBlank()) {
			throw new Exception("Nome do contato é inválido");
		}
		
		if (contato.getTelefone() == null || contato.getTelefone().isBlank()) {
			throw new Exception("Telefone do contato é inválido");
		}
	}
	
	private void validateUpdate(Contato contato) throws Exception {
		if (contato == null) {
			throw new Exception("Contato fornecido é inválido");
		}
		
		if (contato.getId() == null) {
			throw new Exception("ID do contato é inválido");
		}
		
		if (contato.getNome() == null || contato.getNome().isBlank()) {
			throw new Exception("Nome do contato é inválido");
		}
		
		if (contato.getTelefone() == null || contato.getTelefone().isBlank()) {
			throw new Exception("Telefone do contato é inválido");
		}
	}

}
