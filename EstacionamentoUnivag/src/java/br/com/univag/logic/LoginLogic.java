package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.dominio.TipoMsg;
import br.com.univag.exception.DaoException;
import br.com.univag.model.GuaritaVo;
import br.com.univag.model.UsuarioLogadoVo;
import br.com.univag.model.UsuarioVo;
import br.com.univag.service.GuaritaService;
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
public class LoginLogic implements Logica {

    Connection con;
    Mensagem mensagem = new Mensagem();
    UsuarioService serviceUser = new UsuarioService();
    GuaritaService guaritaService = new GuaritaService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mensagem.setRequest(request);
        con = (Connection) request.getAttribute("connection");
        serviceUser.setCon(con);
        guaritaService.setCon(con);

        String email = request.getParameter("emailLogin");
        String senha = request.getParameter("senha");
        String logica = request.getParameter("logica");
        String guarita = request.getParameter("guarita");
        String loginInvalido = "loginInvalido";

        if (logica == null && request.getAttribute("loginInvalido") == null) {

            try {
                request.setAttribute("list", guaritaService.listarGuarita());
            } catch (DaoException ex) {
                ex.getMessage();
            }

            return "loginView.jsp";

        } else if (email != null && logica.equals("verificarUser") && request.getAttribute("loginInvalido") == null) {
            try {
                UsuarioVo vo = serviceUser.autenticarUser(email, senha);
                UsuarioLogadoVo usuarioLogado = new UsuarioLogadoVo();

                if (vo == null) {
                    mensagem.message(TipoMsg.ERRO, "Login ou senha inválidos");
                    request.setAttribute("loginInvalido", loginInvalido);
                    return "Controller?acao=loginUser";

                } else {
                    if (!guarita.equals("")) {
                        int codigoGuarita = Integer.parseInt(guarita);
                       GuaritaVo guaritaVo = guaritaService.selecionarGuaritaByCodigo(codigoGuarita);
                        usuarioLogado.setCodigoGuarita(codigoGuarita);
                        usuarioLogado.setCodigoUser(vo.getCodigo());
                        usuarioLogado.setNomeUsuario(vo.getNome());
                        usuarioLogado.setPerfilUsuario(vo.getPerfil());
                        usuarioLogado.setNomePortaria(guaritaVo.getNome());
                        request.getSession(true).setAttribute("usuarioLogado", usuarioLogado);

                    } else {

                        mensagem.message(TipoMsg.ERRO, "Favor selecionar uma Portaria");
                        request.setAttribute("loginInvalido", loginInvalido);
                        return "Controller?acao=loginUser";
                    }

                    return "sistema/veiculoView.jsp";

                }
            } catch (DaoException ex) {
                ex.getMessage();
            }
        } else if (logica.equals("esqueceuSenha")) {

            return "esqueceuSenha.jsp";

        } else if (logica.equals("novaSenha")) {
            String emailsenha = request.getParameter("emailsenha");
            try {
                UsuarioVo usuario = serviceUser.selecionarUserByEmail(emailsenha);
                if (usuario== null) {
                    System.out.println("Email não existe.");
                    mensagem.message(TipoMsg.ERRO, "Email não Cadastrado no Sistema");
                    return "esqueceuSenha.jsp";

                } else {
                    System.out.println("Email encontrado.");
                    mensagem.message(TipoMsg.INFORMAÇÃO, "Sua nova senha foi enviada para seu Email");
                    serviceUser.recuperUserByEmail(usuario);
                    return "esqueceuSenha.jsp";

                }
            } catch (DaoException ex) {
                ex.getMessage();
            }

        }

        if (request.getAttribute("list") == null) {

            try {
                request.setAttribute("list", guaritaService.listarGuarita());
            } catch (DaoException ex) {
                ex.getMessage();
            }

        }

        return "loginView.jsp";

    }

}
