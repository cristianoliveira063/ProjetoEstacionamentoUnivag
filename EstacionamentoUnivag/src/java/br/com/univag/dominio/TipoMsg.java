/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.dominio;

/**
 *
 * @author CRISTIANO
 */
public enum TipoMsg {

    ERRO("danger"), INFORMAÇÃO("info"), SUCESSO("success"), ALERTA("warning");

    private final String valor;

    private TipoMsg(String valor) {

        this.valor = valor;

    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

}
