/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class RegistrarMovimentoVeiculoLogic implements Logica {

    Mensagem mensagem = new Mensagem();
    MovimentoVeiculoService service = new MovimentoVeiculoService();
    VeiculoService veiculoService = new VeiculoService();
    Connection con;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mensagem.setRequest(request);
        String logica = request.getParameter("logica");
        String placa = request.getParameter("placaVeiculo");
        if (placa == null && logica == null) {

            return "sistema/veiculoView.jsp";

        }

        if (placa != null) {
            placa = placa.trim();
            con = (Connection) request.getAttribute("connection");
            veiculoService.setCon(con);
            try {
                VeiculoVo vo = veiculoService.selectVeiculoByPlaca(removerMascara(placa).toUpperCase());
                if (vo != null) {

                    return "Controller?acao=atualizarInserirVeiculo&codigo=" + vo.getCodigo();

                }
            } catch (DaoException ex) {
                Logger.getLogger(RegistrarMovimentoVeiculoLogic.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (logica.equals("cadastrarVeiculo")) {
            UsuarioLogadoVo usuarioLogado = new UsuarioLogadoVo();
            VeiculoVo veiculo = new VeiculoVo();
            MovimentoVo movimento = new MovimentoVo();
            HttpSession session = ((HttpServletRequest) request).getSession();
            usuarioLogado = (UsuarioLogadoVo) session.getAttribute("usuarioLogado");
            movimento.setUsuario(new UsuarioVo(usuarioLogado.getCodigoUser()));
            movimento.setGuarita(new GuaritaVo(usuarioLogado.getCodigoGuarita()));
            movimento.setStatus(StatusMovimento.ENTRADA.getValor());
            movimento.setStatus_movimento(StatusMovimentoExibir.ATIVO.getValor());
            veiculo.setPlaca(removerMascara(placa).toUpperCase());
            veiculo.setStatus(StatusVeiculo.ATIVO.getValor());
            try {
                service.cadastrarMovimentoVeiculoVo(movimento, veiculo);
                mensagem.message(TipoMsg.INFORMAÇÃO, "Veículo de placa "+ service.maskaraPlca(veiculo.getPlaca()) +" cadastrado com sucesso");

            } catch (DaoException | SQLException | ServiceException | ConexaoException | ClassNotFoundException ex) {
                Logger.getLogger(RegistrarMovimentoVeiculoLogic.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return "sistema/veiculoView.jsp";

    }

    public String removerMascara(String str) {
        str = str.trim();
        return str.replaceAll("[^a-zA-Z0-9 ]", "");
    }

   

}
