package br.com.univag.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public interface Logica {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
