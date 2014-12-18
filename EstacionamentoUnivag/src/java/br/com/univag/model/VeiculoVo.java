package br.com.univag.model;

/**
 *
 * @author CRISTIANO
 */
public class VeiculoVo {

    private int codigo;
    private String placa;
    private int status;

    public VeiculoVo() {
    }

    public VeiculoVo(int codigo) {

        this.codigo = codigo;
    }

    public VeiculoVo(String placa) {

        this.placa = placa;

    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
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
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
