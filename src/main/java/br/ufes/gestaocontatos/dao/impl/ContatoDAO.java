package br.ufes.gestaocontatos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufes.gestaocontatos.dao.interfaces.IContatoDAO;
import br.ufes.gestaocontatos.dao.manager.SqliteManager;
import br.ufes.gestaocontatos.model.Contato;

@Component
public class ContatoDAO implements IContatoDAO {

	@Autowired
	private SqliteManager manager;
	
	@Override
	public Contato insert(Contato contato) throws Exception {
		
		try {
			Connection conn = this.manager.conectar();
			
			String SQL = "INSERT INTO Contato(nome, telefone) VALUES(?, ?);";
			
			this.manager.abreTransacao();
			
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getTelefone());
			ps.executeUpdate();
			
			Contato contatoInserido = getUltimoInserido();
			
			this.manager.fechaTransacao();
			
			this.manager.close();
			
			return contatoInserido;
		} catch (Exception ex) {
			this.manager.desfazTransacao();
			throw new Exception("Erro ao inserir");
		}
	}

	@Override
	public Contato update(Contato contato) throws Exception {
		Connection conn = this.manager.conectar();
		
		String SQL = "UPDATE Contato set nome = ?, telefone = ? WHERE id = ?;";
		
		this.manager.abreTransacao();
		
		PreparedStatement ps = conn.prepareStatement(SQL);
		
		ps.setString(1, contato.getNome());
		ps.setString(2, contato.getTelefone());
		ps.setLong(3, contato.getId());
		
		ps.executeUpdate();
		
		this.manager.fechaTransacao();
		
		this.manager.close();
		
		return contato;
	}

	@Override
	public void delete(Long id) throws Exception {
		Connection conn = this.manager.conectar();
		
		String SQL = "DELETE FROM Contato WHERE id = ?;";
		
		this.manager.abreTransacao();
		
		PreparedStatement ps = conn.prepareStatement(SQL);
		
		ps.setLong(1, id);
		
		ps.executeUpdate();
		
		this.manager.fechaTransacao();
		
		this.manager.close();
	}

	@Override
	public Contato getById(Long id) throws Exception {
		Contato contato = null;
		
		Connection conn = this.manager.conectar();
		
		String SQL = "SELECT nome, telefone FROM Contato WHERE id = ?;";
		
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.setLong(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			String nome = rs.getString(1);
			String telefone = rs.getString(2);
			contato = new Contato(id, nome, telefone);
		}
		
		this.manager.close();
		
		return contato;
	}

	@Override
	public List<Contato> getAll() throws Exception {
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = this.manager.conectar();
		
		String SQL = "SELECT * FROM Contato;";
		PreparedStatement ps = conn.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Long id = (long) rs.getInt(1);
			String nome = rs.getString(2);
			String telefone = rs.getString(3);
			contatos.add(new Contato(id, nome, telefone));
		}
		
		this.manager.close();
		
		return contatos;
	}
	
	private Contato getUltimoInserido() throws Exception {
		Connection conn = this.manager.conectar();
		
		Contato contato = null;
		
		String SQL = "SELECT id, nome, telefone FROM Contato WHERE id = (SELECT max(id) FROM Contato);";
		PreparedStatement ps = conn.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Long id = (long) rs.getInt(1);
			String nome = rs.getString(2);
			String telefone = rs.getString(3);
			contato = new Contato(id, nome, telefone);
		}
		
		return contato;
	}

}
