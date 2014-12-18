/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.dao;

import br.com.univag.exception.ConexaoException;
import br.com.univag.exception.DaoException;
import br.com.univag.fabricaConexao.Conexao;
import br.com.univag.model.VeiculoVo;
import java.sql.Connection;
import org.junit.Test;

/**
 *
 * @author CRISTIANO
 */
public class VeiculoDaoTest {

    public VeiculoDaoTest() {
    }

    /**
     * Test of salvarVeiculo method, of class VeiculoDao.
     *
     * @throws br.com.univag.exception.DaoException
     * @throws br.com.univag.exception.ConexaoException
     */
    @Test
    public void testSalvarVeiculo() throws DaoException, ConexaoException, ClassNotFoundException {
        System.out.println("salvarVeiculo");
        VeiculoVo veiculo = new VeiculoVo();
        veiculo.setPlaca("ed6rf555");
        veiculo.setStatus(2);
        VeiculoDao instance = new VeiculoDao(new Conexao().getConnection());
        instance.salvarVeiculo(veiculo);

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of selecionarVeiculoVoPorPlaca method, of class VeiculoDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSelecionarVeiculoVoPorPlaca() throws Exception {
        System.out.println("selecionarVeiculoVoPorPlaca");
        Connection con = new Conexao().getConnection();
        String placa = "ertfdg";
        VeiculoDao instance = new VeiculoDao(con);
        // VeiculoVo expResult = null;
        @SuppressWarnings("null")
        VeiculoVo result = instance.selecionarVeiculoVoPorPlaca(placa);

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of selecionarVeiculoVoByCodigo method, of class VeiculoDao.
     *
     * @throws java.lang.Exception
     */
   // @Test
    // public void testSelecionarVeiculoVoByCodigo() throws Exception {
    //  System.out.println("selecionarVeiculoVoByCodigo");
    //  int codigo = 0;
    //VeiculoDao instance = null;
    // VeiculoVo expResult = null;
    //  @SuppressWarnings("null")
    //  VeiculoVo result = instance.selecionarVeiculoVoByCodigo(codigo);
    // TODO review the generated test code and remove the default call to fail.
    //}
}
