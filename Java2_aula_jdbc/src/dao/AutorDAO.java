/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        String SQL = "INSERT INTO AUTOR (AUTOR) VALUES (?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setString(1, autor.getNome());
        p.execute();
    }
    
    public void update(Autor autor) {
            
    }
    
    public void delete(Autor autor) {
           
    }
    
    public Autor findById(int id) {
        return new Autor();
    }
    
    public List<Autor> findAll() {
        return new ArrayList<>();
    }
}
