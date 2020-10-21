/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.CondicaoPagamento;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class CondicoesPagamentosDao {
    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarCliente(CondicaoPagamento a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO CondicoesPagamentos("
                    + "descricao_condicao,"
                    + "quantidade_parcela) VALUES("
                    + "'" + a.getDescricao_condicao() + "',"
                    + "" + a.getQuantidade_parcela() + ");";
            JOptionPane.showMessageDialog(null, "Condição de Pagamento salva com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }


//Método Buscar
    public Collection<CondicaoPagamento> BuscarCondicao() {
        Collection<CondicaoPagamento> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM CondicoesPagamentos; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                CondicaoPagamento m = new CondicaoPagamento();
                m.setCod_condicao(c.getResultSet().getInt("cod_condicao"));
                m.setDescricao_condicao(c.getResultSet().getString("descricao_condicao"));
                m.setQuantidade_parcela(c.getResultSet().getInt("quantidade_parcela"));
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