package br.com.univag.dao;

import br.com.univag.exception.DaoException;
import br.com.univag.fabricaConexao.Conexao;
import br.com.univag.model.UsuarioVo;
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
public class UsuarioDao {

    Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public UsuarioDao(Connection con) {
        this.con = con;
    }

    public void salvarUsuarioVo(UsuarioVo vo) throws DaoException {
        String sql = "insert into usuario (nome_usuario,perfil_usuario,senha_usuario,email_usuario,cpf_usuario)values"
                + "(?,?,?,?,?)";
        int i = 1;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(i++, vo.getNome());
            ps.setInt(i++, vo.getPerfil());
            ps.setString(i++, vo.getSenha());
            ps.setString(i++, vo.getEmail());
            ps.setString(i++, vo.getCpf());
            ps.executeUpdate();

        } catch (SQLException ex) {

            throw new DaoException("Erro ao gravar usuario", ex.getCause());

        } finally {

            Conexao.close(ps);

        }

    }

    @SuppressWarnings("null")
    public UsuarioVo autenticarUsuarioVo(String usuario, String senha) throws DaoException {
        String sql = "select * from usuario where email_usuario = ? and senha_usuario = ?";
        UsuarioVo vo = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if (rs.next()) {
                vo = new UsuarioVo();
                vo.setCodigo(rs.getInt(1));
                vo.setNome(rs.getString(2));
                vo.setPerfil(rs.getInt(3));
                vo.setSenha(rs.getString(4));
                vo.setEmail(rs.getString(5));
                vo.setCpf(rs.getString(6));

            }
            if (rs.next()) {

                throw new DaoException("Usuario duplicado");
            }
        } catch (SQLException ex) {

            throw new DaoException("Erro ao retornar usuario", ex.getCause());

        } finally {

            Conexao.close(ps, rs);

        }

        return vo;

    }

    public UsuarioVo selectUsuarioById(int codigo) throws DaoException {
        String sql = "select * from usuario where id_usuario = ?";
        UsuarioVo vo = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                vo = new UsuarioVo();
                vo.setCodigo(rs.getInt(1));
                vo.setNome(rs.getString(2));
                vo.setPerfil(rs.getInt(3));
                vo.setSenha(rs.getString(4));
                vo.setEmail(rs.getString(5));
                vo.setCpf(rs.getString(6));

            }

            if (rs.next()) {

                throw new DaoException("Retornou 2 usuarios");

            }

        } catch (SQLException ex) {

            throw new DaoException("Erro ao selecionar usuario pelo codigo", ex.getCause());
        } finally {

            Conexao.close(ps, rs);

        }

        return vo;

    }

    public List<UsuarioVo> listaUsuarioVo(int registro) throws DaoException {
        String sql = "SELECT * FROM univag_estacionamento_db.usuario LIMIT 10 OFFSET ?";
        List<UsuarioVo> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, registro);
            rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioVo vo = new UsuarioVo();
                vo.setCodigo(rs.getInt(1));
                vo.setNome(rs.getString(2));
                vo.setPerfil(rs.getInt(3));
                vo.setSenha(rs.getString(4));
                vo.setEmail(rs.getString(5));
                vo.setCpf(rs.getString(6));
                list.add(vo);

            }
        } catch (SQLException ex) {

            throw new DaoException("Erro ao listar usuarios", ex.getCause());

        } finally {

            Conexao.close(ps, rs);

        }

        return list;

    }

    public void updateUsuarioVoPerfil(UsuarioVo vo) throws DaoException {
        String sql = "update usuario set nome_usuario = ?,perfil_usuario = ?  where id_usuario = ? ";
        int i = 1;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(i++, vo.getNome());
            ps.setInt(i++, vo.getPerfil());
            ps.setInt(i++, vo.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new DaoException("Erro ao alterar usuario", ex.getCause());

        } finally {

            Conexao.close(ps);

        }

    }

    public UsuarioVo consutarUsuarioPorCpf(String cpf) throws DaoException {
        String sql = "select * from usuario where cpf_usuario = ?";
        UsuarioVo vo = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            if (rs.next()) {
                vo = new UsuarioVo();
                vo.setCodigo(rs.getInt(1));
                vo.setNome(rs.getString(2));
                vo.setPerfil(rs.getInt(3));
                vo.setSenha(rs.getString(4));
                vo.setEmail(rs.getString(5));
                vo.setCpf(rs.getString(6));

            }

            if (rs.next()) {

                throw new DaoException("Erro ao buscar usuario pelo cpf");

            }

        } catch (SQLException ex) {
            throw new DaoException("Erro ao selecionar usuario pelo cpf", ex.getCause());
        } finally {

            Conexao.close(ps, rs);

        }

        return vo;

    }

    public UsuarioVo consultarUsuarioEmail(String email) throws DaoException {
        String sql = "select * from usuario where email_usuario = ?";
        UsuarioVo vo = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                vo = new UsuarioVo();
                vo.setCodigo(rs.getInt(1));
                vo.setNome(rs.getString(2));
                vo.setPerfil(rs.getInt(3));
                vo.setSenha(rs.getString(4));
                vo.setEmail(rs.getString(5));
                vo.setCpf(rs.getString(6));

            }
            if (rs.next()) {

                throw new DaoException("Existem usuarios com emails identicos");

            }

        } catch (SQLException ex) {
            throw new DaoException("Erro ao selecionar usuario pelo email", ex.getCause());
        } finally {

            Conexao.close(ps, rs);

        }

        return vo;

    }

    public int totalUsuarioVo() throws DaoException {
        String sql = "SELECT Count(*) AS quantidade FROM usuario";
        int total = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("quantidade");

            }
        } catch (SQLException ex) {
            throw new DaoException("Erro ao contar tabela", ex.getCause());

        }

        return total;

    }

    public void deleteUser(int codigo) throws DaoException {
        String sql = "delete from usuario where id_usuario=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new DaoException("Erro ao excluir usuario", ex.getCause());
        }

    }
    
     public void updateUsuarioVoSenha(UsuarioVo vo) throws DaoException {
        String sql = "update usuario set senha_usuario = ?  where id_usuario = ? ";
        int i = 1;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(i++, vo.getSenha());
            ps.setInt(i++, vo.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {

            throw new DaoException("Erro ao senha usuario", ex.getCause());

        } finally {

            Conexao.close(ps);

        }

    }
    
    
    

}
