/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.Compras;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class ComprasDao {
    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarDespesa(Compras a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Compras("
                    + "descricao_compra,"
                    + "valor_compra,"
                    + "data_compra,"
                    + "cod_dinheiro) VALUES("
                    + "'" + a.getDescricao_compra() + "',"
                    + "" + a.getValor_compra() + ","
                    + "'"+a.getData_compra()+"',"
                    + "'"+ a.getCod_dinheiro_utilizado()+"');";
            JOptionPane.showMessageDialog(null, "Compra salva com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }


//Método Buscar
    public Collection<Compras > BuscarDespesa() {
        Collection<Compras> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Compras ";

            c.query(sql);
            while (c.getResultSet().next()) {
                Compras m = new Compras();
                m.setCod_compra(c.getResultSet().getInt("cod_compra"));
                m.setDescricao_compra(c.getResultSet().getString("descricao_compra"));
                m.setValor_compra(c.getResultSet().getDouble("valor_compra"));
                m.setData_compra(c.getResultSet().getString("data_compra"));
                m.setCod_dinheiro_utilizado(c.getResultSet().getInt("cod_dinheiro"));
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