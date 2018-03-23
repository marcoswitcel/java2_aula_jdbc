package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Livro;
import util.ConnectionJDBC;

/**
 *
 * @author jw004626
 */
public class LivroDAO {

    Connection connection;

    public LivroDAO() throws Exception {
        this.connection = ConnectionJDBC.getConnection();
    }

    public void save(Livro livro) throws SQLException {
        String SQL = "INSERT INTO LIVRO (LIVRO_ID, EDITORA_ID, TITULO, ANO, DESCRICAO) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, livro.getLivro_id());
        p.setInt(2, livro.getEditora_id());
        p.setString(3, livro.getTitulo());
        p.setInt(4, livro.getAno());
        p.setString(5, livro.getDescricao());
        p.execute();
    }

    public void update(Livro livro) {
        String SQL = "UPDATE LIVRO SET TITULO = ?, ANO = ?, DESCRICAO = ? WHERE LIVRO_ID = ? AND EDITORA_ID = ?;";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, livro.getTitulo());
            p.setInt(2, livro.getAno());
            p.setString(3, livro.getDescricao());
            p.setInt(4, livro.getLivro_id());
            p.setInt(5, livro.getEditora_id());
            p.execute();
            System.out.println("foi");
        } catch (SQLException ex) {
            System.out.println("Erro no update");
        }
    }

    public void delete(Livro livro) {
        String SQL = "DELETE FROM LIVRO WHERE LIVRO_ID = ? AND EDITORA_ID = ?;";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, livro.getLivro_id());
            p.setInt(2, livro.getEditora_id());
            p.execute();
            System.out.println("foi");
        } catch (SQLException ex) {
            System.out.println("Erro no update");
        }
    }

    public Livro findById(int id) {
        return new Livro();
    }

    public List<Livro> findAll() throws Exception {
        // Lista para manter os valores do result set
        List<Livro> list = new ArrayList<>();
        Livro livro;

        String SQL = "SELECT * FROM LIVRO;";
        try {
            // Prepara a SQL
            PreparedStatement p = connection.prepareStatement(SQL);
            // Executa a SQL e armazena os valores no ResultSet rs
            ResultSet rs = p.executeQuery();
            // Navega pelos registros no rs
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                livro = new Livro();
                livro.setLivro_id(rs.getInt("livro_id"));
                livro.setEditora_id(rs.getInt("editora_id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno(rs.getInt("ano"));
                livro.setDescricao(rs.getString("descricao"));
                
                // Inclui na lista
                list.add(livro);
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
