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
import model.Editora;
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
        String SQL = "INSERT INTO LIVRO (EDITORA_ID, TITULO, ANO, DESCRICAO) VALUES (?, ?, ?, ?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setInt(1, livro.getEditora().getEditora_id());
        p.setString(2, livro.getTitulo());
        p.setInt(3, livro.getAno());
        p.setString(4, livro.getDescricao());
        p.execute();
    }

    public void saveAutorLivro(Autor autor, Livro livro) throws Exception {
        String SQL = "INSERT INTO AUTOR_LIVRO VALUES (?, ?);";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, autor.getAutor_id());
            p.setInt(2, livro.getLivro_id());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Livro livro) {
        String SQL = "UPDATE LIVRO SET EDITORA_ID = ?, TITULO = ?, ANO = ?, DESCRICAO = ? WHERE LIVRO_ID = ?;";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, livro.getEditora().getEditora_id());
            p.setString(2, livro.getTitulo());
            p.setInt(3, livro.getAno());
            p.setString(4, livro.getDescricao());
            p.setInt(5, livro.getLivro_id());
            p.execute();
            System.out.println("foi");
        } catch (SQLException ex) {
            System.out.println("Erro no update" + ex.getMessage());
        }
    }

    public void delete(Livro livro) {
        String SQL = "DELETE FROM LIVRO WHERE LIVRO_ID = ?;";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, livro.getLivro_id());
            p.execute();
            System.out.println("foi");
        } catch (SQLException ex) {
            System.out.println("Erro no update" + ex.getMessage());
        }
    }

    public void deleteAutorLivro(Autor autor, Livro livro) throws Exception {
        String SQL = "DELETE FROM AUTOR_LIVRO WHERE AUTOR_ID = ? AND LIVRO_ID = ?;";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, autor.getAutor_id());
            p.setInt(2, livro.getLivro_id());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public Livro findById(int id) throws Exception {

        Livro livro = new Livro();

        String SQL = "SELECT L.*, E.NOME FROM LIVRO L "
                + "INNER JOIN EDITORA E ON E.EDITORA_ID = L.EDITORA_ID "
                + "WHERE LIVRO_ID = ?;";
        try {
            // Prepara a SQL
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, id);
            // Executa a SQL e armazena os valores no ResultSet rs
            ResultSet rs = p.executeQuery();
            // Navega pelos registros no rs
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                livro = new Livro();
                livro.setLivro_id(rs.getInt("livro_id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno(rs.getInt("ano"));
                livro.setDescricao(rs.getString("descricao"));

                Editora editora = new Editora();
                editora.setEditora_id(rs.getInt("editora_id"));
                editora.setNome(rs.getString("nome"));
                
                livro.setEditora(editora);
            }
            // Fechas  as conexões
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        
        livro.setAutores(findAutoresLivro(livro.getLivro_id()));
        return livro;
    }

    public List<Livro> findAll() throws Exception {
        // Lista para manter os valores do result set
        List<Livro> list = new ArrayList<>();
        Livro livro;

        String SQL = "SELECT L.*, E.NOME FROM LIVRO L "
                + "INNER JOIN EDITORA E ON E.EDITORA_ID = L.EDITORA_ID;";
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
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno(rs.getInt("ano"));
                livro.setDescricao(rs.getString("descricao"));

                Editora editora = new Editora();
                editora.setEditora_id(rs.getInt("editora_id"));
                editora.setNome(rs.getString("nome"));

                livro.setEditora(editora);
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
    
    private List<Autor> findAutoresLivro(int livroId) throws Exception{
        List<Autor> lista = new ArrayList<Autor>();
        String SQL = "SELECT AL.AUTOR_ID, A.NOME FROM AUTOR_LIVRO AS AL INNER "
               + "JOIN AUTOR AS A ON A.AUTOR_ID = AL.AUTOR_ID "
               + "WHERE LIVRO_ID = ?;";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, livroId);
            
            ResultSet rs = p.executeQuery();
            
            while(rs.next()) {
                Autor a = new Autor();
                a.setAutor_id(rs.getInt("autor_id"));
                a.setNome(rs.getString("nome"));
                lista.add(a);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
       
       return lista;
    }
}
