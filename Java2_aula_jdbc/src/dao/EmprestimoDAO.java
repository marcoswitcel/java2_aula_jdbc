package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Livro;
import model.Emprestimo;
import model.Estudante;
import util.ConnectionJDBC;

public class EmprestimoDAO {

    Connection connection;

    public EmprestimoDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }

    public void save(Emprestimo emprestimo) throws Exception {
        String SQL = "INSERT INTO EMPRESTIMO(livro_id, estudante_id, data_retirada, data_devolucao, data_entrega, status) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, emprestimo.getLivro().getLivro_id());
            p.setInt(2, emprestimo.getEstudante().getEstudante_id());
            p.setDate(3, new java.sql.Date(emprestimo.getData_retirada().getTime()));
            p.setDate(4, new java.sql.Date(emprestimo.getData_devolucao().getTime()));
            p.setDate(5, new java.sql.Date(emprestimo.getData_entrega().getTime()));
            p.setString(6, emprestimo.getStatus());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Emprestimo emprestimo) throws Exception {
        String SQL = "UPDATE EMPRESTIMO SET LIVRO_ID=?, ESTUDANTE_ID=?, DATA_RETIRADA=?, DATA_DEVOLUCAO=?, DATA_ENTREGA=?, STATUS=?"
                + " WHERE EMPRESTIMO_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, emprestimo.getLivro().getLivro_id());
            p.setInt(2, emprestimo.getEstudante().getEstudante_id());
            p.setDate(3, new java.sql.Date(emprestimo.getData_retirada().getTime()));
            p.setDate(4, new java.sql.Date(emprestimo.getData_devolucao().getTime()));
            p.setDate(5, new java.sql.Date(emprestimo.getData_entrega().getTime()));
            p.setString(6, emprestimo.getStatus());
            p.setInt(7, emprestimo.getEmprestimo_id());
            p.execute();
            System.out.println("Concluiu upd");
        } catch (SQLException ex) {
            System.out.println("SADsadsadsa");
            throw new Exception(ex);
        }
    }

    public void delete(Emprestimo emprestimo) throws Exception {
        String SQL = "DELETE FROM EMPRESTIMO WHERE EMPRESTIMO_ID=?";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, emprestimo.getEmprestimo_id());
            p.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Emprestimo findById(int id) throws Exception {
        Emprestimo objeto = new Emprestimo();
        String SQL = "SELECT e.*, l.TITULO,l.LIVRO_ID, en.ESTUDANTE_ID, en.NOME FROM EMPRESTIMO e "
                + "INNER JOIN LIVRO l ON (l.LIVRO_ID = e.LIVRO_ID) "
                + "INNER JOIN ESTUDANTE en ON (en.ESTUDANTE_ID = e.ESTUDANTE_ID)"
                + " WHERE e.EMPRESTIMO_ID =? ";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Emprestimo();
                objeto.setEmprestimo_id(rs.getInt("Emprestimo_id"));
                objeto.setData_retirada(rs.getDate("Data_retirada"));
                objeto.setData_devolucao(rs.getDate("Data_devolucao"));
                objeto.setData_entrega(rs.getDate("Data_entrega"));
                objeto.setStatus(rs.getString("Status"));

                Estudante estudante = new Estudante();
                estudante.setEstudante_id(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));

                Livro livro = new Livro();
                livro.setLivro_id(rs.getInt("livro_id"));
                livro.setTitulo(rs.getString("titulo"));

                objeto.setEstudante(estudante);
                objeto.setLivro(livro);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return objeto;
    }

    public List<Emprestimo> findAll() throws Exception {
        List<Emprestimo> list = new ArrayList<>();
        Emprestimo objeto;
        String SQL = "SELECT e.*, l.TITULO,l.LIVRO_ID, en.ESTUDANTE_ID, en.NOME FROM EMPRESTIMO e "
                + "INNER JOIN LIVRO l ON (l.LIVRO_ID = e.LIVRO_ID) "
                + "INNER JOIN ESTUDANTE en ON (en.ESTUDANTE_ID = e.ESTUDANTE_ID) "
                + "ORDER BY e.EMPRESTIMO_ID";
        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                objeto = new Emprestimo();
                objeto.setEmprestimo_id(rs.getInt("Emprestimo_id"));
                objeto.setData_retirada(rs.getDate("Data_retirada"));
                objeto.setData_devolucao(rs.getDate("Data_devolucao"));
                objeto.setData_entrega(rs.getDate("Data_entrega"));
                objeto.setStatus(rs.getString("Status"));

                Estudante estudante = new Estudante();
                estudante.setEstudante_id(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));

                Livro livro = new Livro();
                livro.setLivro_id(rs.getInt("livro_id"));
                livro.setTitulo(rs.getString("titulo"));
                
                objeto.setEstudante(estudante);
                objeto.setLivro(livro);
                list.add(objeto);
            }
            rs.close();
            p.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            for (Emprestimo emp : new EmprestimoDAO().findAll()) {
                    System.out.println(emp.getLivro());
            }
        } catch (Exception ex) {
            System.out.println("Deu ruim no emp");
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}