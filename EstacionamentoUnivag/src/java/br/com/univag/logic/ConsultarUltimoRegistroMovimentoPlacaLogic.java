package br.com.univag.logic;

import br.com.univag.controller.Logica;
import br.com.univag.dominio.TipoMsg;
import br.com.univag.exception.DaoException;
import br.com.univag.model.MovimentoVo;
import br.com.univag.service.MovimentoVeiculoService;
import br.com.univag.util.Mensagem;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CRISTIANO
 */
public class ConsultarUltimoRegistroMovimentoPlacaLogic implements Logica {

    Mensagem mensagem = new Mensagem();
    MovimentoVeiculoService service = new MovimentoVeiculoService();
    Connection con;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String logica = request.getParameter("logica");
        String placa = request.getParameter("placa");
        mensagem.setRequest(request);
        con = (Connection) request.getAttribute("connection");
        service.setCon(con);
        
        if(logica !=null){
           
            if(logica.equals("registroMovimento")){
                
                try {
                    placa = placa.trim().toUpperCase();
                    List<MovimentoVo>list = service.listRegistroMov(removerMascara(placa));
                    if(list==null){
                        mensagem.message(TipoMsg.ALERTA, "Não foi encontrado registro do veiculo informado");
                        return "sistema/consultarVeiculoView.jsp";
                        
                    }
                    request.setAttribute("listMovimento",list);
                    return "sistema/listRegistroMovimentoView.jsp";
                } catch (DaoException ex) {
                    
                    ex.getMessage();
                    
                }
                
                
                
            }
            
            
            
            
        }
        
      
        
        
        
        
        
        return "sistema/consultarVeiculoView.jsp";

    }
    
    
      public String removerMascara(String str) {
        str = str.trim();
        return str.replaceAll("[^a-zA-Z0-9 ]", "");
    }

}
