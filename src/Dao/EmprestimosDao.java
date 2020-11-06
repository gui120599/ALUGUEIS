/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.Emprestimos;
import Modelo.Fornecedores;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class EmprestimosDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarEmprestimo(Emprestimos a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Emprestimos("
                    + "cod_fornecedor_emprestimo,"
                    + "desc_emprestimo,"
                    + "situacao_emprestimo,"
                    + "valor_emprestimo) VALUES("
                    + " " + a.getCod_fornecedor_emprestimo() + ","
                    + "'" + a.getDesc_Emprestimo() + "',"
                    + "'"+a.getSituacao_emprestimo()+"',"
                    + " "+a.getValor_emprestimo()+" );";
            JOptionPane.showMessageDialog(null, "Emprestimo salvo com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

//Método Buscar
    public Collection<Emprestimos> BuscarCliente() {
        Collection<Emprestimos> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Emprestimos; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                Emprestimos m = new Emprestimos();
                m.setCod_Emprestimo(c.getResultSet().getInt("cod_emprestimo"));
                m.setCod_fornecedor_emprestimo(c.getResultSet().getInt("cod_fornecedor_emprestimo"));
                m.setDesc_Emprestimo(c.getResultSet().getString("desc_emprestimo"));
                m.setValor_emprestimo(c.getResultSet().getDouble("valor_emprestimo"));
                m.setSituacao_emprestimo(c.getResultSet().getString("situacao_emprestimo"));
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
