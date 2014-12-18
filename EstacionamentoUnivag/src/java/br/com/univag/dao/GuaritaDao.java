package br.com.univag.dao;

import br.com.univag.exception.DaoException;
import br.com.univag.fabricaConexao.Conexao;
import br.com.univag.model.GuaritaVo;
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
public class GuaritaDao {

    Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public GuaritaDao(Connection con) {
        this.con = con;
    }

    public void salvar(GuaritaVo vo) throws DaoException {
        String sql = "insert into guarita (nome_guarita) values (?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNome());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao salvar guarita", ex.getCause());
        } finally {
            Conexao.close(ps);

        }

    }

    public void delete(int codigo) throws DaoException {
        String sql = "delete from  guarita where id_guarita = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("Erro ao deletar guarita", ex.getCause());
        } finally {

            Conexao.close(ps);
        }

    }

    public GuaritaVo selecionar(int codigo) throws DaoException {
        String sql = "select * from guarita where id_guarita = ? ";
        GuaritaVo vo = new GuaritaVo();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {

                vo.setCodigo(rs.getInt(1));
                vo.setNome(rs.getString(2));

            }

            if (rs.next()) {

                throw new DaoException("Registro guarita duplicado");
            }
        } catch (SQLException ex) {
            throw new DaoException("Erro ao selecionar guarita", ex.getCause());
        } finally {

            Conexao.close(ps, rs);

        }
        return vo;

    }

    public void update(GuaritaVo vo) throws DaoException {
        String sql = "update guarita set nome_guarita = ? where id_guarita = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNome());
            ps.setInt(2, vo.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new DaoException("Erro ao alterar guarita", ex.getCause());
        } finally {

            Conexao.close(ps);

        }

    }

    public List<GuaritaVo> listar() throws DaoException {
        String sql = "select * from guarita";
        List<GuaritaVo> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GuaritaVo vo = new GuaritaVo();
                vo.setCodigo(rs.getInt(1));
                vo.setNome(rs.getString(2));
                list.add(vo);

            }
        } catch (SQLException ex) {

            throw new DaoException("Erro ao retornar lista de guarita", ex.getCause());
        } finally {

            Conexao.close(ps, rs);

        }

        return list;

    }

}
