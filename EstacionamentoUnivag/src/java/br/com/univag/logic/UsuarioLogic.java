package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.dominio.PerfilUsuario;
import br.com.univag.dominio.TipoMsg;
import br.com.univag.exception.DaoException;
import br.com.univag.model.UsuarioVo;
import br.com.univag.service.UsuarioService;
import br.com.univag.util.Mensagem;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public class UsuarioLogic implements Logica {

    Connection con;
    Mensagem mensagem = new Mensagem();
    UsuarioService service = new UsuarioService();

    public UsuarioLogic() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logica = request.getParameter("logica");
        mensagem.setRequest(request);
        if (logica != null) {
            if (logica.equalsIgnoreCase("cadastrar")) {
                con = (Connection) request.getAttribute("connection");
                UsuarioVo usuario = new UsuarioVo();
                service.setCon(con);
                String nome = request.getParameter("firstName") + " " + request.getParameter("lastName");
                String cpf = request.getParameter("cpf");
                String email = request.getParameter("email");
                String perfil = request.getParameter("perfil");
                int perfilUsr = Integer.parseInt(perfil);
                usuario.setNome(nome.trim());
                usuario.setEmail(email);
                if (perfilUsr == PerfilUsuario.ADMINISTRADOR.getValor()) {
                    usuario.setPerfil(PerfilUsuario.ADMINISTRADOR.getValor());

                } else {

                    usuario.setPerfil(PerfilUsuario.USUARIO.getValor());

                }
                usuario.setCpf(cpf);

                request.setAttribute("perfil", exibirCadUsr());
                try {
                    int retorno = service.verificarUsuarioVo(usuario);
                    if (retorno == 1) {

                        mensagem.message(TipoMsg.ERRO, "CPF já cadastrado no sistema");
                        System.out.println("---CPF JÁ CADASTRADO---");
                        return "privado/usuarioView.jsp";

                    }
                    if (retorno == 2) {
                        mensagem.message(TipoMsg.ERRO, "EMAIL já cadastrado no sistema");
                        System.out.println("---EMAIL JA CADASTRADO---");
                        return "privado/usuarioView.jsp";
                    }
                    if (retorno == 3) {
                        mensagem.message(TipoMsg.ERRO, "CPF e EMAIL informados já estão cadastrados no sistema");
                        System.out.println("---CPF E EMAIL CADASTRADOS---");
                        return "privado/usuarioView.jsp";

                    }
                    String senha = service.geraSenha();
                    usuario.setSenha(senha);
                    service.salvar(usuario);
                    mensagem.message(TipoMsg.INFORMAÇÃO, "Usuario cadastrado com sucesso");
                } catch (DaoException ex) {
                    System.out.println("Erro UsuarioLogic");
                    ex.getStackTrace();
                }

                return "privado/usuarioView.jsp";

            }

        } else {

            request.setAttribute("perfil", exibirCadUsr());

            return "privado/usuarioView.jsp";

        }

        return null;

    }

    public List<PerfilUsuario> exibirCadUsr() {

        List<PerfilUsuario> list = new ArrayList<>();
        list = new UsuarioService().getPerfil();
        return list;

    }

}
