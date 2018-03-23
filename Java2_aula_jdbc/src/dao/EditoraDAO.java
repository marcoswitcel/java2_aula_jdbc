package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Editora;
import util.ConnectionJDBC;

/**
 *
 * @author jw004626
 */
public class EditoraDAO {

    Connection connection;

    public EditoraDAO() throws Exception {
        this.connection = ConnectionJDBC.getConnection();
    }

    public void save(Editora editora) throws SQLException {
        String SQL = "INSERT INTO EDITORA (NOME, MUNICIPIO) VALUES (?, ?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setString(1, editora.getNome());
        p.setString(2, editora.getMunicipio());
        p.execute();
    }

    public void update(Editora editora) {
        String SQL = "UPDATE EDITORA SET NOME = ?, MUNICIPIO = ? WHERE EDITORA_ID = ?;";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, editora.getNome());
            p.setString(2, editora.getMunicipio());
            p.setInt(3, editora.getEditora_id());
            p.execute();
            System.out.println("foi");
        } catch (SQLException ex) {
            System.out.println("Erro no update");
        }
    }

    public void delete(Editora editora) {
        String SQL = "DELETE FROM EDITORA WHERE EDITORA_ID = ?;";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, editora.getEditora_id());
            p.execute();
            System.out.println("foi");
        } catch (SQLException ex) {
            System.out.println("Erro no update");
        }
    }

    public Editora findById(int id) {
        return new Editora();
    }

    public List<Editora> findAll() throws Exception {
        // Lista para manter os valores do result set
        List<Editora> list = new ArrayList<>();
        Editora editora;

        String SQL = "SELECT * FROM EDITORA;";
        try {
            // Prepara a SQL
            PreparedStatement p = connection.prepareStatement(SQL);
            // Executa a SQL e armazena os valores no ResultSet rs
            ResultSet rs = p.executeQuery();
            // Navega pelos registros no rs
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                editora = new Editora();
                editora.setEditora_id(rs.getInt("editora_id"));
                editora.setNome(rs.getString("nome"));
                editora.setMunicipio(rs.getString("municipio"));
                
                // Inclui na lista
                list.add(editora);
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
