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
public class DinheiroGuardado {
    private int cod_dinheiro;
    private String  local_dinheiro;
    private double valor_guardado;

    public DinheiroGuardado() {
    }

    public DinheiroGuardado(int cod_dinheiro, String local_dinheiro, double valor_guardado) {
        this.cod_dinheiro = cod_dinheiro;
        this.local_dinheiro = local_dinheiro;
        this.valor_guardado = valor_guardado;
    }

    public int getCod_dinheiro() {
        return cod_dinheiro;
    }

    public void setCod_dinheiro(int cod_dinheiro) {
        this.cod_dinheiro = cod_dinheiro;
    }

    public String getLocal_dinheiro() {
        return local_dinheiro;
    }

    public void setLocal_dinheiro(String local_dinheiro) {
        this.local_dinheiro = local_dinheiro;
    }

    public double getValor_guardado() {
        return valor_guardado;
    }

    public void setValor_guardado(double valor_guardado) {
        this.valor_guardado = valor_guardado;
    }
    
    
}
