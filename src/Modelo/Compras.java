/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Suporte T.I 2
 */
public class Compras {
    private int cod_compra;
    private String descricao_compra;
    private double valor_compra;
    private String data_compra;
    private int Cod_dinheiro_utilizado;

    public Compras() {
    }

    public Compras(int cod_compra, String descricao_compra, double valor_compra, String data_compra, int Cod_dinheiro_utilizado) {
        this.cod_compra = cod_compra;
        this.descricao_compra = descricao_compra;
        this.valor_compra = valor_compra;
        this.data_compra = data_compra;
        this.Cod_dinheiro_utilizado = Cod_dinheiro_utilizado;
    }

    public int getCod_compra() {
        return cod_compra;
    }

    public void setCod_compra(int cod_compra) {
        this.cod_compra = cod_compra;
    }

    public String getDescricao_compra() {
        return descricao_compra;
    }

    public void setDescricao_compra(String descricao_compra) {
        this.descricao_compra = descricao_compra;
    }

    public double getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(double valor_compra) {
        this.valor_compra = valor_compra;
    }

    public String getData_compra() {
        return data_compra;
    }

    public void setData_compra(String data_compra) {
        this.data_compra = data_compra;
    }

    public int getCod_dinheiro_utilizado() {
        return Cod_dinheiro_utilizado;
    }

    public void setCod_dinheiro_utilizado(int Cod_dinheiro_utilizado) {
        this.Cod_dinheiro_utilizado = Cod_dinheiro_utilizado;
    }

}
