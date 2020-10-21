/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.ParcelaVenda;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class ParcelaVendaDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarCliente(ParcelaVenda a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO ParcelasEntrada("
                    + "cod_entrada,"
                    + "valor_parcela,"
                    + "data_vencimento,"
                    + "status_paga)VALUES("
                    + "'" + a.getCod_venda() + "',"
                    + "" + a.getValor_parcela() + ","
                    + "'" + a.getData_vencimento() + "',"
                    + "" + a.getStatus_paga() + ");";
            JOptionPane.showMessageDialog(null, "Parcela gerada com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

//Método Buscar
    public Collection<ParcelaVenda> BuscarParcelaVenda() {
        Collection<ParcelaVenda> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM ParcelasVenda ";

            c.query(sql);
            while (c.getResultSet().next()) {
                ParcelaVenda m = new ParcelaVenda();
                m.setCod_venda(c.getResultSet().getInt("cod_entrada"));
                m.setValor_parcela(c.getResultSet().getDouble("valor_parcela"));
                m.setData_vencimento(c.getResultSet().getString("data_vencimento"));
                m.setStatus_paga(c.getResultSet().getBoolean("status_paga"));
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

