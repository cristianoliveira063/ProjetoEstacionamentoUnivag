package br.com.univag.exception;

/**
 *
 * @author CRISTIANO
 */
public class ConexaoException extends Exception {

    private static final long serialVersionUID = 1L;

    public ConexaoException(String message) {
        super(message);
    }

    public ConexaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
