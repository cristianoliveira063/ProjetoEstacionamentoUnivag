
package br.com.univag.logic;

import br.com.univag.controller.Logica;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public class SairDoSistemaLogic implements Logica{
    
    
    
    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        String logica = request.getParameter("logica");
        
        if(logica.equals("confirmarSaida")){
            
            
            return "sistema/sairdoSistemaView.jsp";
            
            
        }
        
        if(logica.equals("sair")){
            
            request.getSession().invalidate();
            
            return "Controller?acao=loginUser";
            
        }
        
     
        
        
        
        
        
        
        return null;
       
    
    }
    
}
