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
public class AlterarExcluirUsuarioLogic implements Logica {

    Connection con;
    Mensagem mensagem = new Mensagem();
    UsuarioService service = new UsuarioService();

    @Override
    @SuppressWarnings("ConvertToStringSwitch")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mensagem.setRequest(request);
        con = (Connection) request.getAttribute("connection");
        UsuarioVo vo = new UsuarioVo();
        service.setCon(con);
        String logica = request.getParameter("logica");

        if (logica.equals("exibir")) {

            try {
                int codigo = Integer.valueOf(request.getParameter("codigo"));
                vo = service.selecionaUserById(codigo);
                String nome = vo.getNome();
                String[] nomeSobrenome = nome.split("\\s");
                vo.setSobrenome(nomeSobrenome[1]);
                vo.setNome(nomeSobrenome[0]);
                request.setAttribute("usuario", vo);
                request.setAttribute("perfil", service.getPerfil());

            } catch (DaoException ex) {
                ex.getMessage();
            }

        } else if (logica.equals("alterar")) {

            String nome = request.getParameter("firstName") + " " + request.getParameter("lastName");
            vo.setNome(nome);
            vo.setCodigo(Integer.valueOf(request.getParameter("codigo")));
            vo.setPerfil(Integer.valueOf(request.getParameter("perfil")));
            vo.setEmail(request.getParameter("email"));
            vo.setSobrenome(request.getParameter("lastName"));
            vo.setCpf(request.getParameter("cpf"));

            try {
                service.AlterarPerfilUser(vo);
                String nomeCompleto = vo.getNome();
                String[] nomeSobrenome = nomeCompleto.split("\\s");
                vo.setSobrenome(nomeSobrenome[1]);
                vo.setNome(nomeSobrenome[0]);
            } catch (DaoException ex) {
                ex.getMessage();
            }
            mensagem.message(TipoMsg.INFORMAÇÃO, "Perfil alterado com sucesso");
            request.setAttribute("usuario", vo);
            request.setAttribute("perfil", service.getPerfil());

        } else if (logica.equals("exibirDelete")) {

            int codigo = Integer.parseInt(request.getParameter("codigo"));
            try {
                vo = service.selecionaUserById(codigo);
                request.setAttribute("usuario", vo);
                return "privado/deleteUserView.jsp";

            } catch (DaoException ex) {
                ex.getMessage();
            }
        } else if (logica.equals("excluirUsr")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            try {
                service.deleteUserById(codigo);
            } catch (DaoException ex) {
                ex.getMessage();
            }

            return "Controller?acao=listagemUsr";

        }

        return "privado/alterarUsuarioView.jsp";

    }

}
