/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.Saidas;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class SaidasDao {
    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Financeiro";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarDespesa(Saidas a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Despesa("
                    + "descricao_despesa,"
                    + "valor_despesa,"
                    + "data_inicial,"
                    + "quantidade_meses) VALUES("
                    + "'" + a.getDescticao_despesa() + "',"
                    + "" + a.getValor_dispesa() + ","
                    + "'"+a.getData_despesa()+"',"
                    + "'"+ a.getQuantidade_meses()+"');";
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }


//Método Buscar
    public Collection<Saidas > BuscarDespesa() {
        Collection<Saidas> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Despesa ";

            c.query(sql);
            while (c.getResultSet().next()) {
                Saidas m = new Saidas();
                m.setCod_despesa(c.getResultSet().getInt("cod_despesa"));
                m.setDescticao_despesa(c.getResultSet().getString("descricao_despesa"));
                m.setValor_dispesa(c.getResultSet().getDouble("valor_despesa"));
                m.setData_despesa(c.getResultSet().getString("data_inicial"));
                m.setQuantidade_meses(c.getResultSet().getInt("quantidade_meses"));
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