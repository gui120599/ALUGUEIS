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
public class ParcelaVenda {
    private int cod_parcela;
    private int cod_venda;
    private double valor_parcela;
    private String data_vencimento;
    private boolean status_paga;

    public ParcelaVenda() {
    }

    public ParcelaVenda(int cod_parcela, int cod_venda, double valor_parcela, String data_vencimento, boolean status_paga) {
        this.cod_parcela = cod_parcela;
        this.cod_venda = cod_venda;
        this.valor_parcela = valor_parcela;
        this.data_vencimento = data_vencimento;
        this.status_paga = status_paga;
    }

    public int getCod_parcela() {
        return cod_parcela;
    }

    public void setCod_parcela(int cod_parcela) {
        this.cod_parcela = cod_parcela;
    }

    public int getCod_venda() {
        return cod_venda;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public double getValor_parcela() {
        return valor_parcela;
    }

    public void setValor_parcela(double valor_parcela) {
        this.valor_parcela = valor_parcela;
    }

    public String getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public boolean getStatus_paga() {
        return status_paga;
    }

    public void setStatus_paga(boolean status_paga) {
        this.status_paga = status_paga;
    }

}
