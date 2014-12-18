/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.dao;

import br.com.univag.exception.DaoException;
import br.com.univag.fabricaConexao.Conexao;
import br.com.univag.model.GuaritaVo;
import br.com.univag.model.MovimentoVo;
import br.com.univag.model.UsuarioVo;
import br.com.univag.model.VeiculoVo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author CRISTIANO
 */
public class MovimentoDao {
    
    Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public MovimentoDao(Connection con) {
        this.con = con;
    }
    
    public void salvarMovimentoVeiculoVo(MovimentoVo movimento) throws DaoException {
        String sql = "insert into movimento (data_registro,veiculo_id,usuario_id,guarita_id,status_registro,status_movimento,data) values "
                + "(?,?,?,?,?,?,?)";
        int i = 1;
        
        try {
            ps = con.prepareStatement(sql);
            ps.setTimestamp(i++, new Timestamp(dataRegistro().getTimeInMillis()));
            ps.setInt(i++, movimento.getVeiculo().getCodigo());
            ps.setInt(i++, movimento.getUsuario().getCodigo());
            ps.setInt(i++, movimento.getGuarita().getCodigo());
            ps.setInt(i++, movimento.getStatus());
            ps.setInt(i++, movimento.getStatus_movimento());
            ps.setDate(i++, new Date(dataRegistro().getTimeInMillis()));
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new DaoException("Erro ao cadastrar movimento", ex.getCause());
        } finally {
            
            Conexao.close(ps);
            
        }
        
    }
    
    public List<MovimentoVo> listaMovimentoVo(int param) throws DaoException {
        String sql = "select id_movimento as codigo, data_registro as data,veiculo.placa_veiculo as veiculo,usuario.nome_usuario as usuario,guarita.nome_guarita\n"
                + "as guarita,status_registro as status from movimento INNER JOIN usuario  on (id_usuario=usuario_id) \n"
                + "inner JOIN veiculo on (id_veiculo=veiculo_id) inner join guarita on (id_guarita=guarita_id)where status_movimento=1 LIMIT 15 OFFSET ? ";
        List<MovimentoVo> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, param);
            rs = ps.executeQuery();
            while (rs.next()) {
                MovimentoVo vo = new MovimentoVo();
                vo.setCodigo(rs.getInt("codigo"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getTimestamp("data"));
                vo.setDataRegistro(data);
                vo.setVeiculo(new VeiculoVo(formatarPlaca(rs.getString("veiculo"))));
                vo.setUsuario(new UsuarioVo(rs.getString("usuario")));
                vo.setGuarita(new GuaritaVo(rs.getString("guarita")));
                vo.setStatus(rs.getInt(6));
                list.add(vo);
                
            }
        } catch (SQLException ex) {
            
            throw new DaoException("Erro ao listar movimentoVo", ex.getCause());
            
        } finally {
            
            Conexao.close(ps, rs);
        }
        
        return list;
        
    }
    
    public MovimentoVo selecionarMovimentoPorCodigo(int codigo) throws DaoException {
        String sql = "select * from movimento where id_movimento = ?";
        MovimentoVo vo = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                vo = new MovimentoVo();
                vo.setCodigo(rs.getInt(1));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getTimestamp(2));
                vo.setDataRegistro(data);
                vo.setVeiculo(new VeiculoVo(rs.getInt(3)));
                vo.setUsuario(new UsuarioVo(rs.getInt(4)));
                vo.setGuarita(new GuaritaVo(rs.getInt(5)));
                vo.setStatus(rs.getInt(6));
                vo.setStatus_movimento(rs.getInt(7));
                Calendar dataRegistro = Calendar.getInstance();
                dataRegistro.setTime(rs.getDate(8));
                vo.setData(dataRegistro);
                
            }
            
            if (rs.next()) {
                
                throw new DaoException("Registro duplicado");
            }
        } catch (SQLException ex) {
            
            throw new DaoException("Erro ao retornar movimento", ex.getCause());
            
        } finally {
            
            Conexao.close(ps, rs);
        }
        
        return vo;
        
    }
    
    public void deleteMovimentoVo(int codigoVeiculo) throws DaoException {
        String sql = "update movimento set status_movimento=2 where veiculo_id=? and status_movimento=1";
        int i = 1;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(i++, codigoVeiculo);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            
            throw new DaoException("Erro ao alterar status movimento", ex.getCause());
            
        } finally {
            
            Conexao.close(ps, rs);
            
        }
        
    }
    
    public List<MovimentoVo> listaMovimentoPorCodigoVeiculo(int codigo) throws DaoException {
        String sql = "select * from movimento where veiculo_id = ?";
        List<MovimentoVo> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                MovimentoVo vo = new MovimentoVo();
                vo.setCodigo(rs.getInt(1));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate(2));
                vo.setDataRegistro(data);
                vo.setVeiculo(new VeiculoVo(rs.getInt(3)));
                vo.setUsuario(new UsuarioVo(rs.getInt(4)));
                vo.setGuarita(new GuaritaVo(rs.getInt(5)));
                vo.setStatus(rs.getInt(6));
                list.add(vo);
                
            }
        } catch (SQLException ex) {
            
            throw new DaoException("Erro ao listar movimentoVo pelo codigo veiculo", ex.getCause());
            
        } finally {
            
            Conexao.close(ps, rs);
        }
        
        return list;
        
    }
    
    public String formatarPlaca(String placaVeiculo) {
        String placa = placaVeiculo.trim();
        String sub = placa.substring(0, 3) + "-" + placa.substring(3);
        
        return sub;
        
    }
    
    public int totalVeiculoMovimentoAtivo() throws DaoException {
        String sql = "SELECT Count(*) AS quantidade FROM movimento where status_movimento = 1 ";
        int total = 0;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("quantidade");
                
            }
        } catch (SQLException ex) {
            throw new DaoException("Erro ao contar tabela movimento", ex.getCause());
            
        }
        
        return total;
        
    }
    
    public MovimentoVo consultarMovimentoVoPorPlacaStatus(String placa, int statusRegistro, int statusMovimento) throws DaoException {
        MovimentoVo movimento = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select id_movimento as codigo, data_registro as data,veiculo.placa_veiculo as veiculo,");
        sql.append("usuario.nome_usuario as usuario,guarita.nome_guarita");
        sql.append(" as guarita,status_registro as status from movimento");
        sql.append(" INNER JOIN usuario  on (id_usuario=usuario_id)");
        sql.append(" inner JOIN veiculo on (id_veiculo=veiculo_id)");
        sql.append(" inner join guarita on (id_guarita=guarita_id)");
        sql.append(" where id_movimento = ");
        sql.append(" (select max(id_movimento )");
        sql.append(" from movimento t");
        sql.append(" where t.veiculo_id = ( select id_veiculo from veiculo v where v.placa_veiculo = ? )");
        sql.append("  and t.status_registro = ?");
        sql.append("  and t.status_movimento = ?   ) ;");
        try {
            ps = con.prepareStatement(sql.toString());
            ps.setString(1, placa);
            ps.setInt(2, statusRegistro);
            ps.setInt(3, statusMovimento);
            rs = ps.executeQuery();
            if (rs.next()) {
                movimento = new MovimentoVo();
                movimento.setCodigo(rs.getInt("codigo"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getTimestamp("data"));
                movimento.setDataRegistro(data);
                movimento.setVeiculo(new VeiculoVo(formatarPlaca(rs.getString("veiculo"))));
                movimento.setUsuario(new UsuarioVo(rs.getString("usuario")));
                movimento.setGuarita(new GuaritaVo(rs.getString("guarita")));
                movimento.setStatus(rs.getInt(6));
                
            }
            if (rs.next()) {
                
                throw new DaoException("retornou mais de um registro movimento");
                
            }
            
        } catch (SQLException ex) {
            throw new DaoException("Erro no Dao consultar registro movimento", ex.getCause());
            
        } finally {
            
            Conexao.close(ps, rs);
            
        }
        
        return movimento;
        
    }
    
    public Calendar dataRegistro() {
        
        Calendar calendar = Calendar.getInstance();
        Date data = new Date(System.currentTimeMillis());
        calendar.setTime(data);
        
        return calendar;
        
    }
    
    public int quantidadeVeiculoByData(Calendar data, int status) throws DaoException {
        String sql = "SELECT Count(*) AS quantidade FROM movimento where status_registro = ? and  data= ? ";
        
        int total = 0;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setDate(2, new Date(data.getTimeInMillis()));
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("quantidade");
                
            }
        } catch (SQLException ex) {
            throw new DaoException("Erro ao contar tabela movimento", ex.getCause());
            
        }
        
        return total;
        
    }
    
     public List<MovimentoVo> listaMovimentoVoByDate(Calendar date,int limite) throws DaoException {
        String sql = "select id_movimento as codigo, data_registro as data,veiculo.placa_veiculo as veiculo,usuario.nome_usuario as usuario,guarita.nome_guarita\n"
                + "as guarita,status_registro as status from movimento INNER JOIN usuario  on (id_usuario=usuario_id) \n"
                + "inner JOIN veiculo on (id_veiculo=veiculo_id) inner join guarita on (id_guarita=guarita_id)where data=? LIMIT 15 OFFSET ?  ";
        List<MovimentoVo> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1, new Date(date.getTimeInMillis()));
            ps.setInt(2, limite);
            rs = ps.executeQuery();
            while (rs.next()) {
                MovimentoVo vo = new MovimentoVo();
                vo.setCodigo(rs.getInt("codigo"));
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getTimestamp("data"));
                vo.setDataRegistro(data);
                vo.setVeiculo(new VeiculoVo(formatarPlaca(rs.getString("veiculo"))));
                vo.setUsuario(new UsuarioVo(rs.getString("usuario")));
                vo.setGuarita(new GuaritaVo(rs.getString("guarita")));
                vo.setStatus(rs.getInt(6));
                list.add(vo);
                
            }
        } catch (SQLException ex) {
            
            throw new DaoException("Erro ao listar movimentoVo", ex.getCause());
            
        } finally {
            
            Conexao.close(ps, rs);
        }
        
        return list;
        
    }
    
     public int totalVeiculoMovimentoAtivoBydata(Calendar data) throws DaoException {
        String sql = "SELECT Count(*) AS quantidade FROM movimento where data = ? ";
        int total = 0;
        try {
            ps = con.prepareStatement(sql);
            ps.setDate(1,new Date(data.getTimeInMillis()));
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("quantidade");
                
            }
        } catch (SQLException ex) {
            throw new DaoException("Erro ao contar tabela movimento", ex.getCause());
            
        }
        
        return total;
        
    }
    
    
}
