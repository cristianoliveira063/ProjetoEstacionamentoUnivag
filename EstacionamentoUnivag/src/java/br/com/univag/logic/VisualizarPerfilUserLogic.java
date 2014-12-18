package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.dominio.TipoMsg;
import br.com.univag.exception.DaoException;
import br.com.univag.model.UsuarioLogadoVo;
import br.com.univag.model.UsuarioVo;
import br.com.univag.service.UsuarioService;
import br.com.univag.util.Mensagem;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CRISTIANO
 */
public class VisualizarPerfilUserLogic implements Logica {

    Connection con;
    Mensagem mensagem = new Mensagem();
    UsuarioService service = new UsuarioService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String logica = request.getParameter("logica");
        con = (Connection) request.getAttribute("connection");
        mensagem.setRequest(request);
        service.setCon(con);
        HttpSession session = ((HttpServletRequest) request).getSession();
        UsuarioLogadoVo usuarioLogado = new UsuarioLogadoVo();
        usuarioLogado = (UsuarioLogadoVo) session.getAttribute("usuarioLogado");
        if (logica.equals("visualizarPerfil")) {

            try {
                UsuarioVo usuario = service.selecionaUserById(usuarioLogado.getCodigoUser());
                String nomeCompleto = usuario.getNome();
                String[] nomeSobrenome = nomeCompleto.split("\\s");
                usuario.setNome(nomeSobrenome[0]);
                usuario.setSobrenome(nomeSobrenome[1]);
                request.setAttribute("usuario", usuario);
            } catch (DaoException ex) {
                Logger.getLogger(VisualizarPerfilUserLogic.class.getName()).log(Level.SEVERE, null, ex);
            }

            return "sistema/perfilUser.jsp";

        }
        if (logica.equals("alterarPerfilUser")) {
            UsuarioVo usuarioParam = new UsuarioVo();
            usuarioParam.setEmail(request.getParameter("email"));
            usuarioParam.setSenha(request.getParameter("senhaAtual"));
            usuarioParam.setPerfil(Integer.parseInt(request.getParameter("perfil")));
            usuarioParam.setNome(request.getParameter("firstName"));
            usuarioParam.setSobrenome(request.getParameter("lastName"));
            usuarioParam.setCpf(request.getParameter("cpf"));
            
            try {
                UsuarioVo vo = service.autenticarUser(usuarioParam.getEmail(),usuarioParam.getSenha());
                if(vo==null){
                    mensagem.message(TipoMsg.ERRO,"Senha inválida");
                    request.setAttribute("usuario", usuarioParam);
                    return "sistema/perfilUser.jsp";
                }
                else if(vo!=null){
                    usuarioParam.setSenha(request.getParameter("password"));
                    usuarioParam.setCodigo(vo.getCodigo());
                    service.updateSenhaUsuarioVoPerfil(usuarioParam);
                     mensagem.message(TipoMsg.SUCESSO,"Informações do usuário alteradas com sucesso");
                    request.setAttribute("usuario",usuarioParam);
                 
                        
                }
                
            } catch (DaoException ex) {
                Logger.getLogger(VisualizarPerfilUserLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }

        return "sistema/perfilUser.jsp";

    }

}
