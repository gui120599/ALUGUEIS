/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.Cliente;
import Modelo.Saldo;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class SaldoDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Financeiro";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarSaldo(Saldo a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Saldo("
                    + "data_saldo,"
                    + "hora_saldo,"
                    + "valor_saldo,) VALUES("
                    + "'" + a.getData_saldo() + "',"
                    + "'" + a.getHora_saldo() + "',"
                    + "" + a.getValor_saldo() + ");";
            JOptionPane.showMessageDialog(null, "Saldo salvo com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

//Método Buscar
    public Collection<Saldo> BuscarSaldo() {
        Collection<Saldo> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Saldo ";

            c.query(sql);
            while (c.getResultSet().next()) {
                Saldo m = new Saldo();
                m.setCod_saldo(c.getResultSet().getInt("cod_saldo"));
                m.setData_saldo(c.getResultSet().getString("data_saldo"));
                m.setHora_saldo(c.getResultSet().getString("hora_saldo"));
                m.setValor_saldo(c.getResultSet().getDouble("Valor_saldo"));
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
