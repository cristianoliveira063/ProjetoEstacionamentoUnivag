package br.com.univag.dominio;

/**
 *
 * @author CRISTIANO
 */
public enum PerfilUsuario {

    ADMINISTRADOR(1), USUARIO(2);

    private final int Valor;

    private PerfilUsuario(int valor) {

        Valor = valor;
    }

    /**
     * @return the Valor
     */
    public int getValor() {
        return Valor;
    }

}
