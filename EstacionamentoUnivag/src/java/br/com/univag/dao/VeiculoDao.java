package br.com.univag.dao;

import br.com.univag.exception.DaoException;
import br.com.univag.fabricaConexao.Conexao;
import br.com.univag.model.VeiculoVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CRISTIANO
 */
public class VeiculoDao {

    Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public VeiculoDao(Connection con) {

        this.con = con;

    }

    public void salvarVeiculo(VeiculoVo veiculo) throws DaoException {
        String sql = "insert into veiculo(placa_veiculo,status_veiculo) values (?,?)";
        int i = 1;

        try {

            stm = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(i++, veiculo.getPlaca());
            stm.setInt(i++, veiculo.getStatus());
            stm.executeUpdate();
            rs = stm.getGeneratedKeys();
            while (rs.next()) {
                veiculo.setCodigo(rs.getInt(1));

            }

        } catch (Exception e) {
            e.getMessage();
            throw new DaoException("Erro no Dao cadastro de veiculos", e.getCause());

        } finally {

            Conexao.close(stm, rs);

        }

    }

    @SuppressWarnings("null")
    public VeiculoVo selecionarVeiculoVoPorPlaca(String placa) throws DaoException {
        String sql = "select * from veiculo where placa_veiculo = ?";
        VeiculoVo veiculo = null;
        int i = 0;
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, placa);
            rs = stm.executeQuery();
            while (rs.next()) {
                veiculo = new VeiculoVo();
                veiculo.setCodigo(rs.getInt(1));
                veiculo.setPlaca(rs.getString(2));
                veiculo.setStatus(rs.getInt(3));
                i++;

            }
            if (i > 1) {

                throw new DaoException("Retornou mais de um registro");
            }

        } catch (SQLException ex) {

            throw new DaoException("Erro retorno veiculo pela placa", ex.getCause());
        } finally {

            Conexao.close(stm, rs);
        }

        return veiculo;

    }

    @SuppressWarnings("null")
    public VeiculoVo selecionarVeiculoVoByCodigo(int codigo) throws DaoException {
        String sql = "select * from veiculo where id_veiculo = ?";
        VeiculoVo veiculo = new VeiculoVo();
        int i = 0;
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            while (rs.next()) {
                veiculo.setCodigo(rs.getInt(1));
                veiculo.setPlaca(rs.getString(2));
                veiculo.setStatus(rs.getInt(3));
                i++;

            }
            if (i > 1) {

                throw new DaoException("Retornou mais de um registro");
            }

        } catch (SQLException ex) {

            throw new DaoException("Erro retorno veiculo pelo codigo", ex.getCause());
        } finally {

            Conexao.close(stm, rs);
        }

        return veiculo;

    }

    public List<VeiculoVo> listVeiculoVo() throws DaoException {
        String sql = "select * from veiculo";
        List<VeiculoVo> lista = new ArrayList<>();
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                VeiculoVo vo = new VeiculoVo();
                vo.setCodigo(rs.getInt(1));
                vo.setPlaca(rs.getString(2));
                vo.setStatus(rs.getInt(3));
                lista.add(vo);

            }
        } catch (SQLException ex) {

            throw new DaoException("Erro na listagem de veiculos", ex.getCause());

        } finally {
            Conexao.close(stm, rs);

        }
        if (lista.size() > 0) {
            return lista;

        }

        return null;

    }

    public void updateStatusVeiculoVo(VeiculoVo vo) throws DaoException {
        String sql = "update veiculo set status_veiculo=? where id_veiculo=?";
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, vo.getStatus());
            stm.setInt(2, vo.getCodigo());
            stm.executeUpdate();
        } catch (SQLException ex) {

            throw new DaoException("Erro ao alterar veiculo", ex.getCause());
        } finally {

            Conexao.close(stm);
        }

    }

    public void deleteVeiculoVo(int codigo) throws DaoException {
        String sql = "delete from veiculo where id_veiculo = ?";
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            stm.executeUpdate();
        } catch (SQLException ex) {

            throw new DaoException("Erro ao excluir veiculo", ex.getCause());
        } finally {
            Conexao.close(stm);
        }

    }

    public int totalVeiculoVoAtivo() throws DaoException {
        String sql = "SELECT Count(*) AS quantidade FROM veiculo where status_veiculo = 1 ";
        int total = 0;
        try {
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("quantidade");

            }
        } catch (SQLException ex) {
            throw new DaoException("Erro ao contar tabela veiculo", ex.getCause());

        }

        return total;

    }

}
