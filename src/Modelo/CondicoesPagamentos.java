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
public class CondicoesPagamentos {
    private int cod_condicao;
    private String descricao_condicao;
    private int quantidade_parcela;

    public CondicoesPagamentos() {
    }

    public CondicoesPagamentos(int cod_condicao, String descricao_condicao, int quantidade_parcela) {
        this.cod_condicao = cod_condicao;
        this.descricao_condicao = descricao_condicao;
        this.quantidade_parcela = quantidade_parcela;
    }

    public int getCod_condicao() {
        return cod_condicao;
    }

    public void setCod_condicao(int cod_condicao) {
        this.cod_condicao = cod_condicao;
    }

    public String getDescricao_condicao() {
        return descricao_condicao;
    }

    public void setDescricao_condicao(String descricao_condicao) {
        this.descricao_condicao = descricao_condicao;
    }

    public int getQuantidade_parcela() {
        return quantidade_parcela;
    }

    public void setQuantidade_parcela(int quantidade_parcela) {
        this.quantidade_parcela = quantidade_parcela;
    }
    @Override
    public String toString() {
        return getDescricao_condicao(); //To change body of generated methods, choose Tools | Templates.
    }
}
