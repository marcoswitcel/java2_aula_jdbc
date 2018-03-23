/*
 * @author jw004626
 */
package model;

import java.util.Date;

public class Estudante {

    private int estudante_id;
    private String nome;
    private String curso;
    private Date data_matricula;// pendente
    private char status;

    public int getEstudante_id() {
        return estudante_id;
    }

    public void setEstudante_id(int estudante_id) {
        this.estudante_id = estudante_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Date getData_matricula() {
        return data_matricula;
    }

    public void setData_matricula(Date data_matricula) {
        this.data_matricula = data_matricula;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
