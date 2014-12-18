package br.com.univag.service;

import br.com.univag.dao.VeiculoDao;
import br.com.univag.exception.DaoException;
import br.com.univag.model.VeiculoVo;
import java.sql.Connection;

/**
 *
 * @author CRISTIANO
 */
public class VeiculoService {

    private Connection con;
    VeiculoVo veiculo = new VeiculoVo();

    public VeiculoService() {

    }

    public void salvarVeiculoService(VeiculoVo vo) throws DaoException {

        new VeiculoDao(con).salvarVeiculo(vo);

    }

    public VeiculoVo selectVeiculoByPlaca(String placa) throws DaoException {

        VeiculoVo vo = new VeiculoDao(con).selecionarVeiculoVoPorPlaca(placa);

        return vo;

    }

    public VeiculoVo selectVeiculoByCodigo(int codigo) throws DaoException {

        VeiculoVo vo = new VeiculoDao(con).selecionarVeiculoVoByCodigo(codigo);

        return vo;

    }

    public void updateStatusBycodigoVeiculo(VeiculoVo vo) throws DaoException {

        new VeiculoDao(con).updateStatusVeiculoVo(vo);

    }

    public int quantidadePaginas() throws DaoException {
        double total = new VeiculoDao(con).totalVeiculoVoAtivo();
        if (total == 15) {

            return 1;

        }

        int resultado = (int) Math.ceil(total / 15);
        return resultado;

    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

}
