package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.exception.DaoException;
import br.com.univag.service.MovimentoVeiculoService;
import br.com.univag.service.VeiculoService;
import br.com.univag.util.Mensagem;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public class ListMovimentoVeiculoAtivoLogic implements Logica {

    Mensagem mensagem = new Mensagem();
    MovimentoVeiculoService service = new MovimentoVeiculoService();
    VeiculoService veiculoService = new VeiculoService();
    Connection con;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mensagem.setRequest(request);
        con = (Connection) request.getAttribute("connection");
        service.setCon(con);
        veiculoService.setCon(con);
        try {
           Integer totalPaginas = service.quantidadePaginas()-1;
           Integer totalVeiculo = service.totalVeiculosDentroDaUnivag();
           request.setAttribute("totalVeiculo",totalVeiculo);
        
            request.setAttribute("totalPgn", totalPaginas);
            String valor = request.getParameter("valor");
           if (valor != null) {
               int parametro = Integer.parseInt(valor);
                request.setAttribute("listMovimento", service.list(parametro));

                return "sistema/listMovimentoView.jsp";

            }

            request.setAttribute("listMovimento", service.list(0));
        } catch (DaoException ex) {
            Logger.getLogger(ListMovimentoVeiculoAtivoLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "sistema/listMovimentoView.jsp";

    }

}
