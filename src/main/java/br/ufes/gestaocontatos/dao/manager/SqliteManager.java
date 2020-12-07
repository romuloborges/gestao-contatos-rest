package br.ufes.gestaocontatos.dao.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class SqliteManager {

	private Connection conn = null;
    
    public Connection conectar() {
        try {
        	if (conn == null || conn.isClosed()) {
        		String url = "jdbc:sqlite:src/main/java/br/ufes/gestaocontatos/dao/db/gestao-contatos";
                conn = DriverManager.getConnection(url);
        	}
        } catch (SQLException ex) {
            Logger.getLogger(SqliteManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
    
    public void close() {
        close(this.conn);
    }
    
    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqliteManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqliteManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqliteManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abreTransacao() throws SQLException {
        if (this.conn == null) {
            throw new RuntimeException("A conexão com o banco não está ativa");
        }
        
        PreparedStatement ps = conn.prepareStatement("BEGIN TRANSACTION;");
        ps.execute();
    }
    
    public void fechaTransacao() throws SQLException {
        if (this.conn == null) {
            throw new RuntimeException("A conexão com o banco não está ativa");
        }
        
        PreparedStatement ps = conn.prepareStatement("COMMIT;");
        ps.execute();
    }
    
    public void desfazTransacao() throws SQLException {
        if (this.conn == null) {
            throw new RuntimeException("A conexão com o banco não está ativa");
        }
        
        PreparedStatement ps = conn.prepareStatement("ROLLBACK;");
        ps.execute();
    }
	
}
