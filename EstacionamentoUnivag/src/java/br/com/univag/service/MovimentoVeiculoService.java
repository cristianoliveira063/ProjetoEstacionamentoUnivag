/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.service;

import br.com.univag.dao.MovimentoDao;
import br.com.univag.exception.ConexaoException;
import br.com.univag.exception.DaoException;
import br.com.univag.exception.ServiceException;
import br.com.univag.fabricaConexao.Conexao;
import br.com.univag.model.MovimentoVo;
import br.com.univag.model.RelatorioVeiculo;
import br.com.univag.model.VeiculoVo;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CRISTIANO
 */
public class MovimentoVeiculoService {

    private Connection con;
    VeiculoService veiculoService = new VeiculoService();

    public MovimentoVeiculoService() {

    }

    public void cadastrarMovimentoVeiculoVo(MovimentoVo movimento, VeiculoVo veiculo) throws DaoException, SQLException, ServiceException, ConexaoException, ClassNotFoundException {

        try {
            con = new Conexao().getConnection();

            con.setAutoCommit(false);
            veiculoService.setCon(con);
            veiculoService.salvarVeiculoService(veiculo);
            movimento.setVeiculo(new VeiculoVo(veiculo.getCodigo()));
            new MovimentoDao(con).salvarMovimentoVeiculoVo(movimento);
            con.commit();

        } catch (SQLException ex) {
            con.rollback();
            throw new ServiceException("Erro ao usar service cadastro de movimento", ex.getCause());

        } finally {

            Conexao.closeConnection(con);

        }

    }

    public void atualizarStatusVeiculoInserirMovimento(VeiculoVo veiculo, MovimentoVo movimento) throws ConexaoException, ClassNotFoundException, DaoException, ServiceException, SQLException {

        Connection conn = new Conexao().getConnection();
        try {
            conn.setAutoCommit(false);
            veiculoService.setCon(conn);
            veiculoService.updateStatusBycodigoVeiculo(veiculo);
            new MovimentoDao(conn).salvarMovimentoVeiculoVo(movimento);
            if (veiculo.getStatus() == 2) {

                new MovimentoDao(conn).deleteMovimentoVo(veiculo.getCodigo());
            }
            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw new ServiceException("Erro ao inserir um registro de movimento de veiculo", ex.getCause());
        } finally {

            Conexao.closeConnection(con);

        }

    }

    public List<MovimentoVo> list(int param) throws DaoException {

        List<MovimentoVo> lista = new MovimentoDao(con).listaMovimentoVo(param);

        return lista;

    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    public int quantidadePaginas() throws DaoException {
        double total = new MovimentoDao(con).totalVeiculoMovimentoAtivo();
        if (total <= 50) {

            return 1;

        }

        int resultado = (int) Math.ceil(total / 50);
        return resultado;

    }

    public void deleteMovimento(int codigo) throws DaoException {

        new MovimentoDao(con).deleteMovimentoVo(codigo);

    }

    public String maskaraPlca(String placa) {

        String mask = new MovimentoDao(con).formatarPlaca(placa);

        return mask;

    }

    public List<MovimentoVo> listRegistroMov(String placa) throws DaoException {
        MovimentoDao dao = new MovimentoDao(con);
        List<MovimentoVo> list = new ArrayList<>();
        placa = placa.trim();
        MovimentoVo vo = dao.consultarMovimentoVoPorPlacaStatus(placa, 1, 1);
        if (vo != null) {
            vo.setStatus_movimento(1);
            list.add(vo);

            return list;

        } else {
            MovimentoVo movEntrada = dao.consultarMovimentoVoPorPlacaStatus(placa, 1, 2);
            if (movEntrada != null) {
                movEntrada.setStatus_movimento(2);
                list.add(movEntrada);

            }

            MovimentoVo movSaida = dao.consultarMovimentoVoPorPlacaStatus(placa, 2, 2);
            if (movSaida != null) {
                movSaida.setStatus_movimento(2);
                list.add(movSaida);
            }
            if (movEntrada == null && movSaida == null) {

                return null;

            }

            return list;

        }

    }

    public MovimentoVo selecionarMovimentoVeiculoByCodigo(int codigo) throws DaoException {

        MovimentoVo vo = new MovimentoDao(con).selecionarMovimentoPorCodigo(codigo);

        return vo;

    }

    public int totalVeiculosDentroDaUnivag() throws DaoException {

        int num = new MovimentoDao(con).totalVeiculoMovimentoAtivo();

        return num;

    }

    public RelatorioVeiculo totalVeiculosBydata(String data) throws ParseException, DaoException {
        MovimentoDao dao = new MovimentoDao(con);
        RelatorioVeiculo relatorio = new RelatorioVeiculo();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        relatorio.setDataMovimento(calendar);
        relatorio.setSaldoEntrada(dao.quantidadeVeiculoByData(calendar, 1));
        relatorio.setSaldoSaida(dao.quantidadeVeiculoByData(calendar, 2));
        relatorio.setAtivo(dao.totalVeiculoMovimentoAtivo());
        Calendar calendarAtual = Calendar.getInstance();
        Date atual = new Date();
        calendarAtual.setTime(atual);
        relatorio.setDataAtual(calendarAtual);

        return relatorio;

    }

    public int quantidadePaginasBydate(String data) throws ParseException, DaoException {
        Calendar calendar = Calendar.getInstance();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        calendar.setTime(date);
        double total = new MovimentoDao(con).totalVeiculoMovimentoAtivoBydata(calendar);
        if (total <= 50) {

            return 1;

        }

        int resultado = (int) Math.ceil(total / 50);
        return resultado;

    }
    
    
    public int totalVeiculosBydate(String data) throws ParseException, DaoException{
       Calendar calendar = Calendar.getInstance();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        calendar.setTime(date);
        int total = new MovimentoDao(con).totalVeiculoMovimentoAtivoBydata(calendar);  
      
        
        return total;
        
        
        
    }

    public List<MovimentoVo> listMovimentoBydate(String data, int pagina) throws ParseException, DaoException {
        Calendar calendar = Calendar.getInstance();
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        calendar.setTime(date);
        List<MovimentoVo> list = new MovimentoDao(con).listaMovimentoVoByDate(calendar, pagina);

        return list;

    }

}
