/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.logic;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
@WebFilter(filterName = "FiltroAutenticador", urlPatterns = {"/*"})
public class FiltroAutenticador implements Filter {

    public FiltroAutenticador() {
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String requestPath = ((HttpServletRequest) request).getRequestURI().toLowerCase();
        System.out.println(requestPath);
        if (requestPath.contains(".jsp")) {
            System.out.println("----TEM JSP----");
            ((HttpServletResponse) response).sendRedirect("../Controller?acao=erroLogin&url=urlnaopermitida");

        }
        System.out.println("-----Entrada----");

        chain.doFilter(request, response);

        System.out.println("-----Saida----");

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
