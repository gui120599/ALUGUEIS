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
    private int cod_saida;
    private String descricao_saida;
    private double valor_saida;
    private String data_saida;
    private int quantidade_meses;

    public Saidas() {
    }

    public Saidas(int cod_despesa, String descticao_despesa, double valor_dispesa, String data_despesa, int quantidade_meses) {
        this.cod_saida = cod_despesa;
        this.descricao_saida = descticao_despesa;
        this.valor_saida = valor_dispesa;
        this.data_saida = data_despesa;
        this.quantidade_meses = quantidade_meses;
    }

    public int getCod_despesa() {
        return cod_saida;
    }

    public void setCod_despesa(int cod_despesa) {
        this.cod_saida = cod_despesa;
    }

    public String getDescticao_despesa() {
        return descricao_saida;
    }

    public void setDescticao_despesa(String descticao_despesa) {
        this.descricao_saida = descticao_despesa;
    }

    public double getValor_dispesa() {
        return valor_saida;
    }

    public void setValor_dispesa(double valor_dispesa) {
        this.valor_saida = valor_dispesa;
    }

    public String getData_despesa() {
        return data_saida;
    }

    public void setData_despesa(String data_despesa) {
        this.data_saida = data_despesa;
    }

    public int getQuantidade_meses() {
        return quantidade_meses;
    }

    public void setQuantidade_meses(int quantidade_meses) {
        this.quantidade_meses = quantidade_meses;
    }
    
    
    
}
