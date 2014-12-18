/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.univag.service;

/**
 *
 * @author CRISTIANO
 */
public class Teste {

    public static void main(String[] args) {

        UsuarioService teste = new UsuarioService();
        String valor = teste.convertMD5("senha");
        System.out.println(valor);

    }

}
