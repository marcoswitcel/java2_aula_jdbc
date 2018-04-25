/*
 * @author jw004626
 */
package model;

import java.util.Date;
import java.util.List;

public class Emprestimo {
    private int emprestimo_id;
    private Livro livro;
    private Estudante estudante;
    private Date data_retirada;
    private Date data_devolucao;
    private Date data_entrega;
    private String status;

    public int getEmprestimo_id() {
        return emprestimo_id;
    }

    public void setEmprestimo_id(int emprestimo_id) {
        this.emprestimo_id = emprestimo_id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Date getData_retirada() {
        return data_retirada;
    }

    public void setData_retirada(Date data_retirada) {
        this.data_retirada = data_retirada;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Date getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}