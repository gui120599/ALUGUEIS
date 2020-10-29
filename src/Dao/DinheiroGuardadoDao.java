/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.DinheiroGuardado;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class DinheiroGuardadoDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarDinheiro(DinheiroGuardado a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO DinheiroGuardado("
                    + "local_guardado,"
                    + "valor_guardado) VALUES("
                    + "'" + a.getLocal_dinheiro() + "',"
                    + "" + a.getValor_guardado() + ");";
            JOptionPane.showMessageDialog(null, "Dinheiro Guardado salvo com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

    public int AtualizarDinheiro(DinheiroGuardado a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "UPDATE DinheiroGuardado SET "
                    + "local_guardado = '" + a.getLocal_dinheiro() + "',"
                    + "valor_guardado = " + a.getValor_guardado() + " "
                    + "WHERE cod_dinheiro = " + a.getCod_dinheiro() + ";";
            JOptionPane.showMessageDialog(null, "Dinheiro Guardado Atualizado com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

//Método Buscar
    public Collection<DinheiroGuardado> BuscarDinheiro() {
        Collection<DinheiroGuardado> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM DinheiroGuardado ";

            c.query(sql);
            while (c.getResultSet().next()) {
                DinheiroGuardado m = new DinheiroGuardado();
                m.setCod_dinheiro(c.getResultSet().getInt("cod_dinheiro"));
                m.setLocal_dinheiro(c.getResultSet().getString("local_guardado"));
                m.setValor_guardado(c.getResultSet().getDouble("valor_guardado"));
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
