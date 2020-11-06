/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.Fornecedores;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class FornecedoresDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarFornecedor(Fornecedores a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Fornecedores("
                    + "nome_fornecedor,"
                    + "cpf_fornecedor,"
                    + "cnpj_fornecedor,"
                    + "telefone_fornecedor,"
                    + "tipo_pessoa) VALUES("
                    + "'" + a.getNome_Fornecedor() + "',"
                    + "'" + a.getCPF_Fornecedor() + "',"
                    + "'"+a.getCNPJ_Fornecedor()+",'"
                    + "'"+a.getTelefone_Fornecedor()+"',"
                    + "'" + a.getTipo_Pessoa() + "');";
            JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

//Método Buscar
    public Collection<Fornecedores> BuscarFornecedor() {
        Collection<Fornecedores> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Fornecedores; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                Fornecedores m = new Fornecedores();
                m.setCod_Fornecedor(c.getResultSet().getInt("cod_fornecedor"));
                m.setNome_Fornecedor(c.getResultSet().getString("nome_fornecedor"));
                m.setCPF_Fornecedor(c.getResultSet().getString("cpf_fornecedor"));
                m.setCNPJ_Fornecedor(c.getResultSet().getString("cnpj_fornecedor"));
                m.setTelefone_Fornecedor(c.getResultSet().getString("telefone_fornecedor"));
                m.setTipo_Pessoa(c.getResultSet().getString("tipo_pessoa"));
                ms.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return ms;
        } finally {
            c.desconectar();
        }
        return ms;
    }
}
