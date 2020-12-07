package br.ufes.gestaocontatos.dao.interfaces;

import java.util.List;

import br.ufes.gestaocontatos.model.Contato;

public interface IContatoDAO {
	
	public Contato insert(Contato contato) throws Exception;
	public Contato update(Contato contato) throws Exception;
	public void delete(Long id) throws Exception;
	public Contato getById(Long id) throws Exception;
	public List<Contato> getAll() throws Exception;

}
