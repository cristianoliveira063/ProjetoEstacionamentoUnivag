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
import br.com.univag.model.UsuarioVo;
import br.com.univag.model.VeiculoVo;
import br.com.univag.service.MovimentoVeiculoService;
import br.com.univag.service.VeiculoService;
import br.com.univag.util.Mensagem;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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
public class AtualizarInserirRegistroVeiculoLogic implements Logica {

    public AtualizarInserirRegistroVeiculoLogic() {
    }

    Mensagem mensagem = new Mensagem();
    MovimentoVeiculoService service = new MovimentoVeiculoService();
    VeiculoService veiculoService = new VeiculoService();
    VeiculoVo veiculoVo = new VeiculoVo();
    MovimentoVo movimentoVo = null;
    Connection con;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mensagem.setRequest(request);
        String codigoVeiculo = request.getParameter("codigo");
        movimentoVo = new MovimentoVo();

        try {
            con = (Connection) request.getAttribute("connection");
            veiculoService.setCon(con);
            int codigo = Integer.parseInt(codigoVeiculo);
            veiculoVo = veiculoService.selectVeiculoByCodigo(codigo);
           

            if (veiculoVo.getStatus() == StatusVeiculo.ATIVO.getValor()) {
               
                
                veiculoVo.setStatus(StatusVeiculo.NAO_ATIVO.getValor());
                movimentoVo.setStatus(StatusMovimento.SAIDA.getValor());
                movimentoVo.setStatus_movimento(StatusMovimentoExibir.INATIVO.getValor());
                
                     

            } else if (veiculoVo.getStatus() == StatusVeiculo.NAO_ATIVO.getValor()) {

                veiculoVo.setStatus(StatusVeiculo.ATIVO.getValor());
                movimentoVo.setStatus(StatusMovimento.ENTRADA.getValor());
                movimentoVo.setStatus_movimento(StatusMovimentoExibir.ATIVO.getValor());

            }
            UsuarioLogadoVo usuarioLogadoVo = new UsuarioLogadoVo();
            HttpSession session = ((HttpServletRequest) request).getSession();
            usuarioLogadoVo = (UsuarioLogadoVo) session.getAttribute("usuarioLogado");
            movimentoVo.setVeiculo(new VeiculoVo(veiculoVo.getCodigo()));
       
            movimentoVo.setGuarita(new GuaritaVo(usuarioLogadoVo.getCodigoGuarita()));
            movimentoVo.setUsuario(new UsuarioVo(usuarioLogadoVo.getCodigoUser()));
           
            try {
                service.atualizarStatusVeiculoInserirMovimento(veiculoVo, movimentoVo);
                if (movimentoVo.getStatus() == StatusMovimento.ENTRADA.getValor()) {

                    mensagem.message(TipoMsg.INFORMAÇÃO, "Entrada do veículo de placa " + service.maskaraPlca(veiculoVo.getPlaca()) + " registrada com sucesso");
                } else if (movimentoVo.getStatus() == StatusMovimento.SAIDA.getValor()) {

                    mensagem.message(TipoMsg.INFORMAÇÃO, "Saída do veículo de placa " + service.maskaraPlca(veiculoVo.getPlaca()) + " registrada com sucesso");
                }

            } catch (ConexaoException | ClassNotFoundException | ServiceException | SQLException ex) {
                Logger.getLogger(AtualizarInserirRegistroVeiculoLogic.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (DaoException ex) {
            Logger.getLogger(AtualizarInserirRegistroVeiculoLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "sistema/veiculoView.jsp";

    }

    public Calendar dataRegistro() {

        Date data = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        return calendar;

    }

}
