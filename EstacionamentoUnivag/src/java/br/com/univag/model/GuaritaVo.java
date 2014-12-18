package br.com.univag.model;

/**
 *
 * @author CRISTIANO
 */
public class GuaritaVo {

    private int codigo;
    private String nome;

    public GuaritaVo() {
    }

    public GuaritaVo(int codigo) {

        this.codigo = codigo;
    }

    public GuaritaVo(String nome) {

        this.nome = nome;

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
