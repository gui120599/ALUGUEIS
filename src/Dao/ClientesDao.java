/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import Modelo.Cliente;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Suporte T.I 2
 */
public class ClientesDao {

    String Tipo_Banco = "Mysql";
    String IP_Banco = "localhost";
    String Porta_Banco = "3306";
    String Nome_Banco = "Aluguel";
    String Usuario_Banco = "root";
    String Senha_Banco = "";
//Método Salvar

    public int SalvarCliente(Cliente a) {
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();
            String sql = "INSERT INTO Clientes("
                    + "nome_cliente,"
                    + "telefone_cliente,"
                    + "cpf_cliente) VALUES("
                    + "'" + a.getNome_cliente() + "',"
                    + "'" + a.getTelefone_cliente() + "',"
                    + "'" + a.getCpf_cliente() + "');";
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
            return c.queryIncluir(sql);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar: " + e);
            return 0;
        }

    }

//Método Buscar
    public Collection<Cliente> BuscarCliente() {
        Collection<Cliente> ms = new ArrayList<>();
        Conexao c = new Conexao(Tipo_Banco, IP_Banco, Porta_Banco, Nome_Banco, Usuario_Banco, Senha_Banco);
        try {
            c.conectar();

            String sql = "SELECT * FROM Clientes; ";

            c.query(sql);
            while (c.getResultSet().next()) {
                Cliente m = new Cliente();
                m.setCod_cliente(c.getResultSet().getInt("cod_cliente"));
                m.setNome_cliente(c.getResultSet().getString("nome_cliente"));
                m.setTelefone_cliente(c.getResultSet().getString("telefone_cliente"));
                m.setCpf_cliente(c.getResultSet().getString("cpf_cliente"));
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
