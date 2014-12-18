package br.com.univag.controller;

import br.com.univag.logic.VerificarParametro;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    VerificarParametro verificar = new VerificarParametro();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // String parametro = request.getParameter("acao");
        verificar.setRequest(request);
        String nomeDaClasse = verificar.getParametro();
        try {

            Class classe = Class.forName(nomeDaClasse);
            Logica logica = (Logica) classe.newInstance();
            String pagina = logica.execute(request, response);
            request.getRequestDispatcher(pagina).forward(request, response);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException | IOException e) {
            throw new ServletException("Erro no controller da aplicação", e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
