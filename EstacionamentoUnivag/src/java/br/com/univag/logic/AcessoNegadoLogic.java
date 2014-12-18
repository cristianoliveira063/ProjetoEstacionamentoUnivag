package br.com.univag.logic;

import br.com.univag.controller.Logica;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public class AcessoNegadoLogic implements Logica {

    public AcessoNegadoLogic() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = request.getParameter("url");
        if (url != null) {
            return "urlErrada.jsp";

        }

        return "acessoNegado.jsp";

    }

}
