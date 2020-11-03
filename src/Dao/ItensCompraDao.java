/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.ItensCompra;
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
public class ItensCompraDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarItem(ItensCompra a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO ItensCompra("
                    + "cod_compra,"
                    + "descricao_item_compra,"
                    + "quantidade,"
                    + "valor_item_compra,"
                    + "valor_total_item_compra"
                    + ") VALUES("
                    + "" + a.getCod_compra() + ","
                    + "'" + a.getDescricao_item_compra() + "',"
                    + "" + a.getQuantidade() + ","
                    + "" + a.getValor_item_compra() + ","
                    + "" + a.getValor_total_item_compra() + ");";
            JOptionPane.showMessageDialog(null, "Item registrado com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }
    
    //Método Excluir
    public int RemoverItensCompra(int Codigo) {
        int qtdRegistrosAfetados = 0;
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "DELETE FROM ItensCompra WHERE cod_item_compra= " + Codigo + " ;";
            JOptionPane.showMessageDialog(null, "Item Removido!");
            qtdRegistrosAfetados = c.queryUpdate(sql);
            return qtdRegistrosAfetados;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Remover: " + e);
            return qtdRegistrosAfetados;
        } finally {
            c.desconectar();
        }
    }

//Método Buscar
        public Collection<ItensCompra> BuscarItensCompra(int cod_compra) {
        Collection<ItensCompra> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM ItensCompra WHERE cod_compra = " + cod_compra + "; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                ItensCompra m = new ItensCompra();
                m.setCod_item_compra(c.getResultSet().getInt("cod_item_compra"));
                m.setCod_compra(c.getResultSet().getInt("Cod_compra"));
                m.setDescricao_item_compra(c.getResultSet().getString("descricao_item_compra"));
                m.setQuantidade(c.getResultSet().getInt("quantidade"));
                m.setValor_item_compra(c.getResultSet().getDouble("valor_item_compra"));
                m.setValor_total_item_compra(c.getResultSet().getDouble("valor_total_item_compra"));
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
