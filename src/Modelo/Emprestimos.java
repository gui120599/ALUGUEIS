/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author guilherme.santos
 */
public class Emprestimos {
    private int Cod_Emprestimo;
    private int Cod_fornecedor_emprestimo;
    private String Desc_Emprestimo;
    private double valor_emprestimo;
    private String situacao_emprestimo;
    

    public Emprestimos() {
    }

    public Emprestimos(int Cod_Emprestimo, int Cod_fornecedor_emprestimo, String Desc_Emprestimo, double valor_emprestimo, String situacao_emprestimo) {
        this.Cod_Emprestimo = Cod_Emprestimo;
        this.Cod_fornecedor_emprestimo = Cod_fornecedor_emprestimo;
        this.Desc_Emprestimo = Desc_Emprestimo;
        this.valor_emprestimo = valor_emprestimo;
        this.situacao_emprestimo = situacao_emprestimo;
    }

    public int getCod_Emprestimo() {
        return Cod_Emprestimo;
    }

    public void setCod_Emprestimo(int Cod_Emprestimo) {
        this.Cod_Emprestimo = Cod_Emprestimo;
    }

    public int getCod_fornecedor_emprestimo() {
        return Cod_fornecedor_emprestimo;
    }

    public void setCod_fornecedor_emprestimo(int Cod_fornecedor_emprestimo) {
        this.Cod_fornecedor_emprestimo = Cod_fornecedor_emprestimo;
    }

    public String getDesc_Emprestimo() {
        return Desc_Emprestimo;
    }

    public void setDesc_Emprestimo(String Desc_Emprestimo) {
        this.Desc_Emprestimo = Desc_Emprestimo;
    }

    public double getValor_emprestimo() {
        return valor_emprestimo;
    }

    public void setValor_emprestimo(double valor_emprestimo) {
        this.valor_emprestimo = valor_emprestimo;
    }

    public String getSituacao_emprestimo() {
        return situacao_emprestimo;
    }

    public void setSituacao_emprestimo(String situacao_emprestimo) {
        this.situacao_emprestimo = situacao_emprestimo;
    }

}
