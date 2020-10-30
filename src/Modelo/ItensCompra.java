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
public class ItensCompra {
    private int cod_item_compra;
    private int cod_compra;
    private String descricao_item_compra;
    private int quantidade;
    private double valor_item_compra;
    private double valor_total_item_compra;

    public ItensCompra() {
    }

    public ItensCompra(int cod_item_compra, int cod_compra, String descricao_item_compra, int quantidade, double valor_item_compra, double valor_total_item_compra) {
        this.cod_item_compra = cod_item_compra;
        this.cod_compra = cod_compra;
        this.descricao_item_compra = descricao_item_compra;
        this.quantidade = quantidade;
        this.valor_item_compra = valor_item_compra;
        this.valor_total_item_compra = valor_total_item_compra;
    }

    public int getCod_item_compra() {
        return cod_item_compra;
    }

    public void setCod_item_compra(int cod_item_compra) {
        this.cod_item_compra = cod_item_compra;
    }

    public int getCod_compra() {
        return cod_compra;
    }

    public void setCod_compra(int cod_compra) {
        this.cod_compra = cod_compra;
    }

    public String getDescricao_item_compra() {
        return descricao_item_compra;
    }

    public void setDescricao_item_compra(String descricao_item_compra) {
        this.descricao_item_compra = descricao_item_compra;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_item_compra() {
        return valor_item_compra;
    }

    public void setValor_item_compra(double valor_item_compra) {
        this.valor_item_compra = valor_item_compra;
    }

    public double getValor_total_item_compra() {
        return valor_total_item_compra;
    }

    public void setValor_total_item_compra(double valor_total_item_compra) {
        this.valor_total_item_compra = valor_total_item_compra;
    }

}
