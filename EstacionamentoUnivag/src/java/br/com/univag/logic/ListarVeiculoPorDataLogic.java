/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.exception.DaoException;
import br.com.univag.service.MovimentoVeiculoService;
import br.com.univag.util.Mensagem;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public class ListarVeiculoPorDataLogic implements Logica {

    MovimentoVeiculoService movimentoService = new MovimentoVeiculoService();
    Mensagem mensagem = new Mensagem();
    Connection con;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        con = (Connection) request.getAttribute("connection");
        movimentoService.setCon(con);
        mensagem.setRequest(request);
        String data = request.getParameter("data");

        try {
            if (data != null) {
                 Integer totalPaginas = movimentoService.quantidadePaginasBydate(data)-1;
                 Integer totalVeiculo = movimentoService.totalVeiculosBydate(data);
                 request.setAttribute("totalVeiculo",totalVeiculo);
                  request.setAttribute("totalPgn", totalPaginas);
                   String valor = request.getParameter("valor");
              if (valor != null) {
               int parametro = Integer.parseInt(valor);
                request.setAttribute("listMovimento", movimentoService.listMovimentoBydate(data,parametro));
                request.setAttribute("data",data);
                return "sistema/listRegistroMovimentoBydateView.jsp";

            }
             
                request.setAttribute("listMovimento", movimentoService.listMovimentoBydate(data, 0));
                request.setAttribute("data",data);
                return "sistema/listRegistroMovimentoBydateView.jsp";

            }

        } catch (ParseException | DaoException ex) {
            Logger.getLogger(ListarVeiculoPorDataLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "sistema/dataRegistroMovimentoView.jsp";

    }

}
