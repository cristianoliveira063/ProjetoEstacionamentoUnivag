/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.service;

import br.com.univag.dao.GuaritaDao;
import br.com.univag.exception.DaoException;
import br.com.univag.model.GuaritaVo;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author CRISTIANO
 */
public class GuaritaService {

    private Connection con;

    public GuaritaService() {
    }

    public List<GuaritaVo> listarGuarita() throws DaoException {

        List<GuaritaVo> lista = new GuaritaDao(con).listar();

        return lista;

    }
    
    
    public GuaritaVo selecionarGuaritaByCodigo(int codigo) throws DaoException{
        
        GuaritaVo vo = new GuaritaDao(con).selecionar(codigo);
        
        return vo;
        
        
        
    }
    

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

}
