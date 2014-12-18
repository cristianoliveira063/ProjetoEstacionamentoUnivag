package br.com.univag.util;

import br.com.univag.dominio.TipoMsg;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author CRISTIANO
 */
public class Mensagem {

    private HttpServletRequest request;

    public Mensagem() {

    }

    public void message(TipoMsg msg, String message) {

        MensagemModel mensagem = new MensagemModel();
        mensagem.setMsg(message);
        switch (msg) {
            case SUCESSO:
                mensagem.setTipoMsg(msg.SUCESSO.getValor());
                System.out.println(msg.SUCESSO.getValor());
                break;
            case ERRO:
                mensagem.setTipoMsg(msg.ERRO.getValor());
                break;
            case INFORMAÇÃO:
                mensagem.setTipoMsg(msg.INFORMAÇÃO.getValor());
                break;
            case ALERTA:
                mensagem.setTipoMsg(msg.ALERTA.getValor());
                break;
        }

        this.request.setAttribute("msg", mensagem);
    }

    /**
     * @param request the request to set
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

}
