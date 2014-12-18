/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.model;

/**
 *
 * @author CRISTIANO
 */
public class UsuarioLogadoVo {

    private int codigoUser;
    private String nomeUsuario;
    private String nomePortaria;
    private int perfilUsuario;
    private int codigoGuarita;

    /**
     * @return the codigoUser
     */
    public int getCodigoUser() {
        return codigoUser;
    }

    /**
     * @param codigoUser the codigoUser to set
     */
    public void setCodigoUser(int codigoUser) {
        this.codigoUser = codigoUser;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * @return the perfilUsuario
     */
    public int getPerfilUsuario() {
        return perfilUsuario;
    }

    /**
     * @param perfilUsuario the perfilUsuario to set
     */
    public void setPerfilUsuario(int perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    /**
     * @return the codigoGuarita
     */
    public int getCodigoGuarita() {
        return codigoGuarita;
    }

    /**
     * @param codigoGuarita the codigoGuarita to set
     */
    public void setCodigoGuarita(int codigoGuarita) {
        this.codigoGuarita = codigoGuarita;
    }

    /**
     * @return the nomePortaria
     */
    public String getNomePortaria() {
        return nomePortaria;
    }

    /**
     * @param nomePortaria the nomePortaria to set
     */
    public void setNomePortaria(String nomePortaria) {
        this.nomePortaria = nomePortaria;
    }

}
