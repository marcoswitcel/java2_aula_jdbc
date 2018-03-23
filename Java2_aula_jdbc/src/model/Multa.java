/*
 * @author jw004626
 */
package model;

public class Multa {

    private int multa_id;
    private int emprestimo_id;
    private double valor;

    public int getMulta_id() {
        return multa_id;
    }

    public void setMulta_id(int multa_id) {
        this.multa_id = multa_id;
    }

    public int getEmprestimo_id() {
        return emprestimo_id;
    }

    public void setEmprestimo_id(int emprestimo_id) {
        this.emprestimo_id = emprestimo_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
