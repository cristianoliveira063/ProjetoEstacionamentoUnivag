package br.com.univag.dominio;

/**
 *
 * @author CRISTIANO
 */
public enum StatusMovimento {

    ENTRADA(1), SAIDA(2);

    private final int Valor;

    private StatusMovimento(int valor) {

        Valor = valor;
    }

    /**
     * @return the Valor
     */
    public int getValor() {
        return Valor;
    }

}
