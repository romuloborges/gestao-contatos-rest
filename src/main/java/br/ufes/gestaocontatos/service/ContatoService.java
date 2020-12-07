package br.ufes.gestaocontatos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.gestaocontatos.business.ContatoBusiness;
import br.ufes.gestaocontatos.model.Contato;
import br.ufes.gestaocontatos.model.dto.ContatoDTO;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoBusiness contatoBusiness;
	
	public ContatoDTO insert(ContatoDTO contato) throws Exception {
		Contato contatoBanco = contatoBusiness.insert(mapeiaDTOEmEntidade(contato));
		
		return mapeiaEntidadeEmDTO(contatoBanco);
	}
	
	public ContatoDTO update(ContatoDTO contato) throws Exception {
		Contato contatoBanco = contatoBusiness.update(mapeiaDTOEmEntidade(contato));
		
		return mapeiaEntidadeEmDTO(contatoBanco);
	}
	
	public void delete(Long id) throws Exception {
		contatoBusiness.delete(id);
	}
	
	public ContatoDTO getById(Long id) throws Exception {
		Contato contatoBanco = contatoBusiness.getById(id);
		
		return mapeiaEntidadeEmDTO(contatoBanco);
	}
	
	public List<ContatoDTO> getAll() throws Exception {
		List<Contato> contatosBanco = contatoBusiness.getAll();
		List<ContatoDTO> contatosDTO = new ArrayList<>();
		
		for(Contato contato : contatosBanco) {
			contatosDTO.add(mapeiaEntidadeEmDTO(contato));
		}
		
		return contatosDTO;
	}
	
	private Contato mapeiaDTOEmEntidade(ContatoDTO contatoDTO) throws Exception {
		if (contatoDTO == null) {
			throw new Exception("Contato inválido");
		}
		
		return new Contato(contatoDTO.getId(), contatoDTO.getNome(), contatoDTO.getTelefone());
	}
	
	private ContatoDTO mapeiaEntidadeEmDTO(Contato contato) throws Exception {
		if (contato == null) {
			throw new Exception("Contato inválido");
		}
		
		return new ContatoDTO(contato.getId(), contato.getNome(), contato.getTelefone());
	}

}
