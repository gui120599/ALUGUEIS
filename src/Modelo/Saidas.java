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
public class Saidas {
    private int cod_despesa;
    private String descricao_despesa;
    private double valor_dispesa;
    private String data_despesa;
    private int quantidade_meses;

    public Saidas() {
    }

    public Saidas(int cod_despesa, String descticao_despesa, double valor_dispesa, String data_despesa, int quantidade_meses) {
        this.cod_despesa = cod_despesa;
        this.descricao_despesa = descticao_despesa;
        this.valor_dispesa = valor_dispesa;
        this.data_despesa = data_despesa;
        this.quantidade_meses = quantidade_meses;
    }

    public int getCod_despesa() {
        return cod_despesa;
    }

    public void setCod_despesa(int cod_despesa) {
        this.cod_despesa = cod_despesa;
    }

    public String getDescticao_despesa() {
        return descricao_despesa;
    }

    public void setDescticao_despesa(String descticao_despesa) {
        this.descricao_despesa = descticao_despesa;
    }

    public double getValor_dispesa() {
        return valor_dispesa;
    }

    public void setValor_dispesa(double valor_dispesa) {
        this.valor_dispesa = valor_dispesa;
    }

    public String getData_despesa() {
        return data_despesa;
    }

    public void setData_despesa(String data_despesa) {
        this.data_despesa = data_despesa;
    }

    public int getQuantidade_meses() {
        return quantidade_meses;
    }

    public void setQuantidade_meses(int quantidade_meses) {
        this.quantidade_meses = quantidade_meses;
    }
    
    
    
}
