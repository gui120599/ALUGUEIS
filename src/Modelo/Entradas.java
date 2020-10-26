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
public class Entradas {
    private int cod_venda;
    private int cod_cliente;
    private String descricao_venda;
    private double valor_custo;
    private double valor_venda;
    private double valor_lucro;
    private double valor_desconto;
    private String data_venda;
    private int cod_condicao_pagamento;
    private int cod_tipo_pagamento;

    public Entradas() {
    }

    public Entradas(int cod_venda, int cod_cliente, String descricao_venda, double valor_custo, double valor_venda, double valor_lucro, double valor_desconto, String data_venda, int cod_condicao_pagamento, int cod_tipo_pagamento) {
        this.cod_venda = cod_venda;
        this.cod_cliente = cod_cliente;
        this.descricao_venda = descricao_venda;
        this.valor_custo = valor_custo;
        this.valor_venda = valor_venda;
        this.valor_lucro = valor_lucro;
        this.valor_desconto = valor_desconto;
        this.data_venda = data_venda;
        this.cod_condicao_pagamento = cod_condicao_pagamento;
        this.cod_tipo_pagamento = cod_tipo_pagamento;
    }

    public int getCod_venda() {
        return cod_venda;
    }

    public void setCod_venda(int cod_venda) {
        this.cod_venda = cod_venda;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getDescricao_venda() {
        return descricao_venda;
    }

    public void setDescricao_venda(String descricao_venda) {
        this.descricao_venda = descricao_venda;
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

    public double getValor_desconto() {
        return valor_desconto;
    }

    public void setValor_desconto(double valor_desconto) {
        this.valor_desconto = valor_desconto;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public int getCod_condicao_pagamento() {
        return cod_condicao_pagamento;
    }

    public void setCod_condicao_pagamento(int cod_condicao_pagamento) {
        this.cod_condicao_pagamento = cod_condicao_pagamento;
    }

    public int getCod_tipo_pagamento() {
        return cod_tipo_pagamento;
    }

    public void setCod_tipo_pagamento(int cod_tipo_pagamento) {
        this.cod_tipo_pagamento = cod_tipo_pagamento;
    }

   
}
