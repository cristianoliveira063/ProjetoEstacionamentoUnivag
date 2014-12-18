package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.dominio.TipoMsg;
import br.com.univag.exception.DaoException;
import br.com.univag.model.UsuarioVo;
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
public class PesquisarUsuarioLogic implements Logica {

    Connection con;
    Mensagem mensagem = new Mensagem();
    UsuarioService service = new UsuarioService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mensagem.setRequest(request);
        con = (Connection) request.getAttribute("connection");
        service.setCon(con);
        String logica = request.getParameter("logica");
        String pesquisa = request.getParameter("pesquisar");

        if (logica == null) {

            return "privado/pesquisarUsuarioView.jsp";

        } else if (logica.equals("parametroPesquisa")) {
            UsuarioVo vo = new UsuarioVo();
            if (validarParametroPesquisa(pesquisa) == 1) {
                vo.setCpf(pesquisa);
                try {
                    request.setAttribute("listUser", service.pesquisaUsuarioService(vo));

                } catch (DaoException ex) {
                    ex.getMessage();
                }

            } else if (validarParametroPesquisa(pesquisa) == 2) {
                vo.setEmail(pesquisa);
                try {

                    request.setAttribute("listUser", service.pesquisaUsuarioService(vo));
                } catch (DaoException ex) {
                    ex.getMessage();
                }

            } else if (validarParametroPesquisa(pesquisa) == 0) {
                mensagem.message(TipoMsg.ERRO, "Valor de pesquisa inválido");
                return "privado/pesquisarUsuarioView.jsp";

            }

        }

        return "privado/listUsuarioView.jsp";

    }

    public int validarParametroPesquisa(String valor) {
        int validacao = 0;
        String regexEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String regexCpf = "[0-9]{11}";

        if (valor.matches(regexCpf)) {

            validacao = 1;

        } else if (valor.matches(regexEmail)) {

            validacao = 2;

        }

        return validacao;

    }

}
