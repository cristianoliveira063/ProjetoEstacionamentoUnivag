/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.logic;

import br.com.univag.dominio.PerfilUsuario;
import br.com.univag.model.UsuarioLogadoVo;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public class VerificarParametro {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private String verificarParametro(String key) {
        Map<String, String> logica = new HashMap<>();
        logica.put("teste", "TesteLogic");
        logica.put("usuario", "UsuarioLogic");
        logica.put("listagemUsr", "ListaUsuarioLogic");
        logica.put("alterarUsr", "AlterarExcluirUsuarioLogic");
        logica.put("deletarUsr", "AlterarExcluirUsuarioLogic");
        logica.put("loginUser", "LoginLogic");
        logica.put("pesquisarUsr", "PesquisarUsuarioLogic");
        logica.put("veiculo", "RegistrarMovimentoVeiculoLogic");
        logica.put("erroLogin", "AcessoNegadoLogic");
        logica.put("atualizarInserirVeiculo", "AtualizarInserirRegistroVeiculoLogic");
        logica.put("listagemMov", "ListMovimentoVeiculoAtivoLogic");
        logica.put("sairSistema","SairDoSistemaLogic");
        logica.put("perfilusuario","VisualizarPerfilUserLogic");
        logica.put("registroveiculo","ConsultarUltimoRegistroMovimentoPlacaLogic");
        logica.put("saidalist","RegistrarSaidaDaListRegistroLogic");
        logica.put("relatorio", "RelatorioVeiculoLogic");
        logica.put("relatoriodata","ListarVeiculoPorDataLogic");

        Map<String, String> acessoPrivado = new HashMap<>();
        acessoPrivado.put("listagemUsr", "ListaUsuarioLogic");
        acessoPrivado.put("alterarUsr", "AlterarExcluirUsuarioLogic");
        acessoPrivado.put("deletarUsr", "AlterarExcluirUsuarioLogic");
        acessoPrivado.put("pesquisarUsr", "PesquisarUsuarioLogic");
        acessoPrivado.put("usuario", "UsuarioLogic");

        UsuarioLogadoVo usuario = new UsuarioLogadoVo();

        usuario = (UsuarioLogadoVo) request.getSession().getAttribute("usuarioLogado");
        if (usuario != null) {
            if (usuario.getPerfilUsuario() == PerfilUsuario.ADMINISTRADOR.getValor()) {

                return logica.get(key);

            } else {
                boolean valor = acessoPrivado.containsKey(key);
                if (valor) {

                    return "AcessoNegadoLogic";

                }

            }

        }
        return logica.get(key);

    }

    public String getParametro() {

        String param = request.getParameter("acao");

        if (param == null || request.getSession().getAttribute("usuarioLogado") == null) {

            param = "loginUser";

        }

        String classe = "br.com.univag.logic." + verificarParametro(param);
        return classe;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

}
