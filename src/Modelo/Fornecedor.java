/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author guilherme.santos
 */
public class Fornecedor {
    private int Cod_Fornecedor;
    private String Nome_Fornecedor;
    private String CPF_Fornecedor;
    private String CNPJ_Fornecedor;
    private String Telefone_Fornecedor;

    public Fornecedor() {
    }

    public Fornecedor(int Cod_Fornecedor, String Nome_Fornecedor, String CPF_Fornecedor, String CNPJ_Fornecedor, String Telefone_Fornecedor) {
        this.Cod_Fornecedor = Cod_Fornecedor;
        this.Nome_Fornecedor = Nome_Fornecedor;
        this.CPF_Fornecedor = CPF_Fornecedor;
        this.CNPJ_Fornecedor = CNPJ_Fornecedor;
        this.Telefone_Fornecedor = Telefone_Fornecedor;
    }

    public int getCod_Fornecedor() {
        return Cod_Fornecedor;
    }

    public void setCod_Fornecedor(int Cod_Fornecedor) {
        this.Cod_Fornecedor = Cod_Fornecedor;
    }

    public String getNome_Fornecedor() {
        return Nome_Fornecedor;
    }

    public void setNome_Fornecedor(String Nome_Fornecedor) {
        this.Nome_Fornecedor = Nome_Fornecedor;
    }

    public String getCPF_Fornecedor() {
        return CPF_Fornecedor;
    }

    public void setCPF_Fornecedor(String CPF_Fornecedor) {
        this.CPF_Fornecedor = CPF_Fornecedor;
    }

    public String getCNPJ_Fornecedor() {
        return CNPJ_Fornecedor;
    }

    public void setCNPJ_Fornecedor(String CNPJ_Fornecedor) {
        this.CNPJ_Fornecedor = CNPJ_Fornecedor;
    }

    public String getTelefone_Fornecedor() {
        return Telefone_Fornecedor;
    }

    public void setTelefone_Fornecedor(String Telefone_Fornecedor) {
        this.Telefone_Fornecedor = Telefone_Fornecedor;
    }
    
    
    
}
