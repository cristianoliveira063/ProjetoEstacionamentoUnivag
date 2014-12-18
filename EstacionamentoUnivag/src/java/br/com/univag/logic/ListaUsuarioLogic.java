package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.exception.DaoException;
import br.com.univag.service.UsuarioService;
import br.com.univag.util.Mensagem;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public class ListaUsuarioLogic implements Logica {

    Connection con;
    Mensagem mensagem = new Mensagem();
    UsuarioService service = new UsuarioService();

    public ListaUsuarioLogic() {

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mensagem.setRequest(request);
        con = (Connection) request.getAttribute("connection");
        service.setCon(con);
        try {
            Integer totalPaginas = service.quantidadePaginas();
            request.setAttribute("totalPgn", totalPaginas - 1);
            String valor = request.getParameter("valor");
            if (valor != null) {
                int parametro = Integer.parseInt(valor);
                request.setAttribute("listUser", service.listUsuario(parametro));

                return "privado/listUsuarioView.jsp";

            }

            request.setAttribute("listUser", service.listUsuario(0));

        } catch (DaoException ex) {
            ex.getMessage();
        }

        return "privado/listUsuarioView.jsp";

    }

}
