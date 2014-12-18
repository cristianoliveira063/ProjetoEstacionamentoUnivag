package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.dominio.StatusMovimento;
import br.com.univag.dominio.StatusMovimentoExibir;
import br.com.univag.dominio.StatusVeiculo;
import br.com.univag.dominio.TipoMsg;
import br.com.univag.exception.ConexaoException;
import br.com.univag.exception.DaoException;
import br.com.univag.exception.ServiceException;
import br.com.univag.model.GuaritaVo;
import br.com.univag.model.MovimentoVo;
import br.com.univag.model.UsuarioLogadoVo;
import br.com.univag.model.VeiculoVo;
import br.com.univag.service.MovimentoVeiculoService;
import br.com.univag.service.VeiculoService;
import br.com.univag.util.Mensagem;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
public class RegistrarSaidaDaListRegistroLogic implements Logica {

    VeiculoService veiculoService = new VeiculoService();
    MovimentoVeiculoService movimentoService = new MovimentoVeiculoService();
    Mensagem mensagem = new Mensagem();
    Connection con;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        con = (Connection) request.getAttribute("connection");
        veiculoService.setCon(con);
        movimentoService.setCon(con);
        mensagem.setRequest(request);
        String codigoMovimento = request.getParameter("codigo");
        String logica = request.getParameter("logica");
        String placa = request.getParameter("placa");
        VeiculoVo veiculo = new VeiculoVo();
        MovimentoVo movimento = new MovimentoVo();
        HttpSession session = ((HttpServletRequest) request).getSession();
        UsuarioLogadoVo usuarioLogado = (UsuarioLogadoVo) session.getAttribute("usuarioLogado");

        if (logica != null) {
            if (logica.equals("registrarSaidaList")) {
                int codigo = Integer.parseInt(codigoMovimento);
                try {
                    movimento = movimentoService.selecionarMovimentoVeiculoByCodigo(codigo);
                    veiculo.setCodigo(movimento.getVeiculo().getCodigo());
                    veiculo.setStatus(StatusVeiculo.NAO_ATIVO.getValor());
                    movimento.setGuarita(new GuaritaVo(usuarioLogado.getCodigoGuarita()));
                    movimento.setVeiculo(new VeiculoVo(veiculo.getCodigo()));
                    movimento.setStatus(StatusMovimento.SAIDA.getValor());
                    movimento.setStatus_movimento(StatusMovimentoExibir.INATIVO.getValor());
                    movimentoService.atualizarStatusVeiculoInserirMovimento(veiculo, movimento);
                    mensagem.message(TipoMsg.INFORMAÇÃO, "Saída do veiculo " + placa + " realizada com sucesso");

                } catch (DaoException | ConexaoException | ClassNotFoundException | ServiceException | SQLException ex) {
                    Logger.getLogger(RegistrarSaidaDaListRegistroLogic.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        return "sistema/veiculoView.jsp";

    }

}
