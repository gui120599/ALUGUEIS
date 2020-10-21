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
public class Saldo {

    private int cod_saldo;
    private String data_saldo;
    private String hora_saldo;
    private double valor_saldo;

    public Saldo() {
    }

    public Saldo(int cod_saldo, String data_saldo, String hora_saldo, double valor_saldo) {
        this.cod_saldo = cod_saldo;
        this.data_saldo = data_saldo;
        this.hora_saldo = hora_saldo;
        this.valor_saldo = valor_saldo;
    }

    public int getCod_saldo() {
        return cod_saldo;
    }

    public void setCod_saldo(int cod_saldo) {
        this.cod_saldo = cod_saldo;
    }

    public String getData_saldo() {
        return data_saldo;
    }

    public void setData_saldo(String data_saldo) {
        this.data_saldo = data_saldo;
    }

    public String getHora_saldo() {
        return hora_saldo;
    }

    public void setHora_saldo(String hora_saldo) {
        this.hora_saldo = hora_saldo;
    }

    public double getValor_saldo() {
        return valor_saldo;
    }

    public void setValor_saldo(double valor_saldo) {
        this.valor_saldo = valor_saldo;
    }
    
}
