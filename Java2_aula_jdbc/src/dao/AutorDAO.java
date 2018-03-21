/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Autor;
import util.ConnectionJDBC;

/**
 *
 * @author jw004626
 */
public class AutorDAO {
    
    Connection connection;
    
    public AutorDAO() throws Exception {
        this.connection = ConnectionJDBC.getConnection();
    }
    
    public void save(Autor autor) throws SQLException {
        String SQL = "INSERT INTO AUTOR (NOME) VALUES (?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setString(1, autor.getNome());
        p.execute();
    }
    
    public void update(Autor autor) {
        String SQL = "UPDATE AUTOR SET NOME = ? WHERE AUTOR_ID = ?;";
        
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, autor.getNome());
            p.setInt(2, autor.getAutor_id());
            p.execute();
        } catch (SQLException ex) {
        }
    }
    
    public void delete(Autor autor) {
           
    }
    
    public Autor findById(int id) {
        return new Autor();
    }
    
    public List<Autor> findAll() throws Exception {
        // Lista para manter os valores do result set
        List<Autor> list = new ArrayList<>();
        Autor autor;
        
        String SQL = "SELECT * FROM AUTOR;";
        try {
            // Prepara a SQL
            PreparedStatement p = connection.prepareStatement(SQL);
            // Executa a SQL e armazena os valores no ResultSet rs
            ResultSet rs = p.executeQuery();
            // Navega pelos registros no rs
            while(rs.next()) {
                // Instancia a classe e informa os valores do BD
                autor = new Autor();
                autor.setAutor_id(rs.getInt("autor_id"));
                autor.setNome(rs.getString("nome"));
                // Inclui na lista
                list.add(autor);
            }
            // Fechas  as conexões
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        
        
        return list;
    }
}
