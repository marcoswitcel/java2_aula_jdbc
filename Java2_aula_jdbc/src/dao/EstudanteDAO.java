/*
 * @author jw004626
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Estudante;
import util.ConnectionJDBC;

public class EstudanteDAO {
    
    Connection connection;
    
    public EstudanteDAO() throws Exception {
        this.connection = ConnectionJDBC.getConnection();
    }
        public void save(Estudante estudante) throws SQLException {
        String SQL = "INSERT INTO ESTUDANTE (NOME, CURSO, DATA_MATRICULA, STATUS) VALUES (?, ?, ?, ?)";
        PreparedStatement p = connection.prepareStatement(SQL);
        p.setString(1, estudante.getNome());
        p.setString(2, estudante.getCurso());
        p.setDate(3, estudante.getData_matricula());
        p.setString(4, "" + estudante.getStatus());
        p.execute();
    }

    public void update(Estudante estudante) {
        String SQL = "UPDATE ESTUDANTE SET NOME = ?, CURSO = ?, DATA_MATRICULA = ?, STATUS = ? WHERE ESTUDANTE_ID = ?;";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setString(1, estudante.getNome());
            p.setString(2, estudante.getCurso());
            p.setDate(3, estudante.getData_matricula());
            p.setString(4, ""+estudante.getStatus());
            p.setInt(5, estudante.getEstudante_id());
            p.execute();
            System.out.println("foi");
        } catch (SQLException ex) {
            System.out.println("Erro no update:" + ex.getMessage());
        }
    }

    public void delete(Estudante estudante) {
        String SQL = "DELETE FROM ESTUDANTE WHERE ESTUDANTE_ID = ?;";

        try {
            PreparedStatement p = connection.prepareStatement(SQL);
            p.setInt(1, estudante.getEstudante_id());
            p.execute();
            System.out.println("foi");
        } catch (SQLException ex) {
            System.out.println("Erro no update");
        }
    }

    public Estudante findById(int id) {
        return new Estudante();
    }

    public List<Estudante> findAll() throws Exception {
        // Lista para manter os valores do result set
        List<Estudante> list = new ArrayList<>();
        Estudante estudante;

        String SQL = "SELECT * FROM ESTUDANTE;";
        try {
            // Prepara a SQL
            PreparedStatement p = connection.prepareStatement(SQL);
            // Executa a SQL e armazena os valores no ResultSet rs
            ResultSet rs = p.executeQuery();
            // Navega pelos registros no rs
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                estudante = new Estudante();
                estudante.setEstudante_id(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setCurso(rs.getString("curso"));
                estudante.setData_matricula(rs.getDate("data_matricula"));
                estudante.setStatus(rs.getString("status").charAt(0));
                
                // Inclui na lista
                list.add(estudante);
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
