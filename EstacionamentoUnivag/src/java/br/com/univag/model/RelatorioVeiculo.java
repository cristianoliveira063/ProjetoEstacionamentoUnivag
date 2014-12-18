
package br.com.univag.model;

import java.util.Calendar;

/**
 *
 * @author CRISTIANO
 */
public class RelatorioVeiculo {
    
    private Calendar dataAtual;
    private Calendar dataMovimento;
    private int saldoEntrada;
    private int saldoSaida;
    private int ativo;

    /**
     * @return the dataMovimento
     */
    public Calendar getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(Calendar dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the saldoEntrada
     */
    public int getSaldoEntrada() {
        return saldoEntrada;
    }

    /**
     * @param saldoEntrada the saldoEntrada to set
     */
    public void setSaldoEntrada(int saldoEntrada) {
        this.saldoEntrada = saldoEntrada;
    }

    /**
     * @return the saldoSaida
     */
    public int getSaldoSaida() {
        return saldoSaida;
    }

    /**
     * @param saldoSaida the saldoSaida to set
     */
    public void setSaldoSaida(int saldoSaida) {
        this.saldoSaida = saldoSaida;
    }

    /**
     * @return the ativo
     */
    public int getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the dataAtual
     */
    public Calendar getDataAtual() {
        return dataAtual;
    }

    /**
     * @param dataAtual the dataAtual to set
     */
    public void setDataAtual(Calendar dataAtual) {
        this.dataAtual = dataAtual;
    }
    
   
    
    
    
}
