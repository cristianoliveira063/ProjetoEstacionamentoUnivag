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
public class RelatorioVeiculoLogic implements Logica {

    Connection con;
    MovimentoVeiculoService movimentoService = new MovimentoVeiculoService();
    Mensagem mensagem = new Mensagem();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logica = request.getParameter("logica");
        String data = request.getParameter("data");
        mensagem.setRequest(request);
        con = (Connection) request.getAttribute("connection");
        movimentoService.setCon(con);

        if (logica != null) {
            if (logica.equals("listRelatorio")) {

                try {
                    request.setAttribute("list", movimentoService.totalVeiculosBydata(data.trim()));
                    return "sistema/relatorioVeiculosView.jsp";
                } catch (ParseException | DaoException ex) {
                    Logger.getLogger(RelatorioVeiculoLogic.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        return "sistema/dataRegistroView.jsp";

    }

}
