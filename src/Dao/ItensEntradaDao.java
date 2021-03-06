/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.ItensEntrada;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class ItensEntradaDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarItem(ItensEntrada a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO ItensEntrada("
                    + "cod_entrada,"
                    + "descricao_item_entrada,"
                    + "quantidade,"
                    + "valor_custo,"
                    + "valor_venda,"
                    + "valor_lucro,"
                    + "valor_total"
                    + ") VALUES("
                    + "" + a.getCod_venda() + ","
                    + "'" + a.getDescricao_item() + "',"
                    + "" + a.getQuantidade() + ","
                    + "" + a.getValor_custo() + ","
                    + "" + a.getValor_venda() + ","
                    + "" + a.getValor_lucro() + ","
                    + "" + a.getValor_total() + ");";
            JOptionPane.showMessageDialog(null, "Item registrado com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

//Método Excluir
    public int RemoverItensEntrada(int Codigo) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "DELETE FROM ItensEntrada WHERE cod_item_entrada= " + Codigo + " ;";
            JOptionPane.showMessageDialog(null, "Item Removido!");
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar: " + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }

//Método Buscar
    public Collection<ItensEntrada> BuscarItens(int cod_entrada) {
        Collection<ItensEntrada> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM ItensEntrada WHERE cod_entrada = " + cod_entrada + "; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                ItensEntrada m = new ItensEntrada();
                m.setCod_item(c.getResultSet().getInt("cod_item_entrada"));
                m.setCod_venda(c.getResultSet().getInt("Cod_entrada"));
                m.setDescricao_item(c.getResultSet().getString("descricao_item_entrada"));
                m.setQuantidade(c.getResultSet().getInt("quantidade"));
                m.setValor_custo(c.getResultSet().getDouble("valor_custo"));
                m.setValor_venda(c.getResultSet().getDouble("valor_venda"));
                m.setValor_lucro(c.getResultSet().getDouble("valor_lucro"));
                m.setValor_total(c.getResultSet().getDouble("valor_total"));
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
    public Collection<ItensEntrada> BuscarValorTotal(int cod_entrada) {
        Collection<ItensEntrada> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT SUM(valor_venda),SUM(valor_lucro),SUM(valor_total) FROM ItensEntrada WHERE cod_entrada = " + cod_entrada + "; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                ItensEntrada m = new ItensEntrada();
                m.setCod_item(c.getResultSet().getInt("cod_item_entrada"));
                m.setCod_venda(c.getResultSet().getInt("Cod_entrada"));
                m.setDescricao_item(c.getResultSet().getString("descricao_item_entrada"));
                m.setQuantidade(c.getResultSet().getInt("quantidade"));
                m.setValor_custo(c.getResultSet().getDouble("valor_custo"));
                m.setValor_venda(c.getResultSet().getDouble("valor_venda"));
                m.setValor_lucro(c.getResultSet().getDouble("valor_lucro"));
                m.setValor_total(c.getResultSet().getDouble("valor_total"));
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
