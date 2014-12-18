package br.com.univag.model;

import java.util.Calendar;

/**
 *
 * @author CRISTIANO
 */
public class MovimentoVo {

    private int codigo;
    private Calendar dataRegistro;
    private VeiculoVo veiculo;
    private UsuarioVo usuario;
    private GuaritaVo guarita;
    private int status;
    private int status_movimento;
    private Calendar data;

    public MovimentoVo() {
    }

    public MovimentoVo(int codigo) {
        this.codigo = codigo;

    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the dataRegistro
     */
    public Calendar getDataRegistro() {
        return dataRegistro;
    }

    /**
     * @param dataRegistro the dataRegistro to set
     */
    public void setDataRegistro(Calendar dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    /**
     * @return the veiculo
     */
    public VeiculoVo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(VeiculoVo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * @return the usuario
     */
    public UsuarioVo getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioVo usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the guarita
     */
    public GuaritaVo getGuarita() {
        return guarita;
    }

    /**
     * @param guarita the guarita to set
     */
    public void setGuarita(GuaritaVo guarita) {
        this.guarita = guarita;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the status_movimento
     */
    public int getStatus_movimento() {
        return status_movimento;
    }

    /**
     * @param status_movimento the status_movimento to set
     */
    public void setStatus_movimento(int status_movimento) {
        this.status_movimento = status_movimento;
    }

    /**
     * @return the data
     */
    public Calendar getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Calendar data) {
        this.data = data;
    }

}
