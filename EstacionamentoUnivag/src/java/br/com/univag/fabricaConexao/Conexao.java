package br.com.univag.fabricaConexao;

import br.com.univag.exception.ConexaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author CRISTIANO Classe que gera conexão com a base de dados.
 */
public class Conexao {

    Connection con;
    private static ResourceBundle config;
    private static String url;
    private static String usr;
    private static String senha;

    public Conexao() {

        config = ResourceBundle.getBundle("br.com.univag.fabricaConexao.mysql");
        url = config.getString("url");
        usr = config.getString("usr");
        senha = config.getString("senha");

    }

    public Connection getConnection() throws ConexaoException, ClassNotFoundException {

        try {
            System.out.println("Classe Conexao");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usr, senha);
            System.out.println("--Conexão realizada com sucesso--");
        } catch (SQLException e) {
            System.out.println("Erro Classe Conexão");
            throw new ConexaoException("erro", e.getCause());

        }

        return con;

    }

    public static void closeConnection(Connection con) {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {

                System.err.println("--Erro ao fechar a conexão--");
            }

        }

    }

    public static void close(PreparedStatement stm, ResultSet rs) {
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException ex) {
                ex.getMessage();
                System.err.println("1.--Erro ao fechar PreparedStatement");

            }

        }
        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException ex) {
                ex.getMessage();
                System.err.println("--Erro ao fechar ResultSet---");
            }
        }

    }

    public static void close(PreparedStatement stm) {

        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException ex) {
                ex.getMessage();
                System.err.println("2.--Erro ao fechar PreparedStatement");

            }

        }

    }
}
