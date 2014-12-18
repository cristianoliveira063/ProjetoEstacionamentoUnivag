/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.fabricaConexao;

import br.com.univag.exception.ConexaoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author CRISTIANO
 */
@WebFilter(filterName = "FiltroConexao", urlPatterns = {"/*"})
public class FiltroConexao implements Filter {

    public FiltroConexao() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            //Abre a conexão

            Connection connection = new Conexao().getConnection();
            request.setAttribute("connection", connection);
            System.out.println("Conexao aberta");

            chain.doFilter(request, response);
            //Fecha a conexão
            try {
                if (connection != null) {
                    connection.close();

                }

                System.out.println("Conexao fechada");
            } catch (SQLException e) {
                e.getMessage();
                System.out.println("erro ao fechar o filtro de conexão");
            }
        } catch (ConexaoException ex) {

            System.out.println("Erro conexao");

            ex.getMessage();
        } catch (ClassNotFoundException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
