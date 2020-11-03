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
public class ItensEntrada {
    private int cod_item;
    private int cod_venda;
    private String descricao_item;
    private int quantidade;
    private double valor_custo;
    private double valor_venda;
    private double valor_lucro;
    private double valor_total;

    public ItensEntrada() {
    }

    public ItensEntrada(int cod_item, int cod_venda, String descricao_item, int quantidade, double valor_custo, double valor_venda, double valor_lucro, double valor_total) {
        this.cod_item = cod_item;
        this.cod_venda = cod_venda;
        this.descricao_item = descricao_item;
        this.quantidade = quantidade;
        this.valor_custo = valor_custo;
        this.valor_venda = valor_venda;
        this.valor_lucro = valor_lucro;
        this.valor_total = valor_total;
    }

    public int getCod_item() {
        return cod_item;
    }

    public void setCod_item(int cod_item) {
        this.cod_item = cod_item;
    }

    public int getCod_venda() {
        return cod_venda;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public String getDescricao_item() {
        return descricao_item;
    }

    public void setDescricao_item(String descricao_item) {
        this.descricao_item = descricao_item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_custo() {
        return valor_custo;
    }

    public void setValor_custo(double valor_custo) {
        this.valor_custo = valor_custo;
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }

    public double getValor_lucro() {
        return valor_lucro;
    }

    public void setValor_lucro(double valor_lucro) {
        this.valor_lucro = valor_lucro;
    }
    
    public double getValor_total(){
        return valor_total;
    }
    
    public void setValor_total(double valor_lucro){
        this.valor_total = valor_lucro;
    }
    
}
