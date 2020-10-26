/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.Clientes;
import Modelo.TiposPagamentos;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class TiposPagamentosDao {
    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Financeiro";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarTipoPagamento(TiposPagamentos a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO TipoPagamento("
                    + "descricao_tipo_pagamento) VALUES("
                    + "'" + a.getDescricao_tipo_pagamento() + "');";
            JOptionPane.showMessageDialog(null, "Tipo Pagamento salvo com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }


//Método Buscar
    public Collection<TiposPagamentos> BuscarTipoPagamento() {
        Collection<TiposPagamentos> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM TipoPagamento ";

            c.query(sql);
            while (c.getResultSet().next()) {
                TiposPagamentos m = new TiposPagamentos();
                m.setCod_tipo_pagamento(c.getResultSet().getInt("cod_tipo_pagamento"));
                m.setDescricao_tipo_pagamento(c.getResultSet().getString("descricao_tipo_pagamento"));
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