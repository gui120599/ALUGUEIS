/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.Entradas;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class EntradasDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
    
    public int AbrirVenda(double a) {
        
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO entradas(valor_entrada)VALUES("+ a +")";
            JOptionPane.showMessageDialog(null, "Venda Aberta!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }
//Método Salvar

    public int SalvarEntrada(Entradas a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE Entradas SET"
                    + "cod_cliente =" + a.getCod_cliente() + ","
                    + "descricao_entrada ='" + a.getDescricao_venda() + "',"
                    + "valor_custo =" + a.getValor_custo() + ","
                    + "valor_entrada =" + a.getValor_venda() + ","
                    + "valor_lucro =" + a.getValor_lucro() + ","
                    + "valor_desconto =" + a.getValor_desconto() + ","
                    + "cod_condicao_pagamento =" + a.getCod_condicao_pagamento() + ","
                    + "cod_tipo_pagamento =" + a.getCod_tipo_pagamento() + ","
                    + "data_entrada" + a.getData_venda() + ""
                    + "WHERE cod_entrada = "+a.getCod_venda()+");";
            JOptionPane.showMessageDialog(null, "Entrada registrada com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

//Método Buscar
    public Collection<Entradas > BuscarVendas() {
        Collection<Entradas> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Entradas ; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                Entradas m = new Entradas();
                m.setCod_venda(c.getResultSet().getInt("Cod_entrada"));
                m.setDescricao_venda(c.getResultSet().getString("descricao_entrada"));
                m.setCod_condicao_pagamento(c.getResultSet().getInt("cod_condicao_pagamento"));
                m.setCod_tipo_pagamento(c.getResultSet().getInt("cod_tipo_pagamento"));
                m.setValor_custo(c.getResultSet().getDouble("valor_custo"));
                m.setValor_venda(c.getResultSet().getDouble("valor_entrada"));
                m.setValor_lucro(c.getResultSet().getDouble("valor_lucro"));
                m.setValor_desconto(c.getResultSet().getDouble("valor_desconto"));
                m.setData_venda(c.getResultSet().getString("data_entrada"));
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
//Método Buscar
    public Collection<Entradas > BuscarUltimaVenda() {
        Collection<Entradas> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT cod_entrada FROM Entradas ORDER BY cod_entrada DESC LIMIT 1; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                Entradas m = new Entradas();
                m.setCod_venda(c.getResultSet().getInt("Cod_entrada"));
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
