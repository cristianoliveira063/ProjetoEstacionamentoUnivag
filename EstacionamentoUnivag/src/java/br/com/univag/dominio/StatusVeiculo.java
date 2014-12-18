package br.com.univag.dominio;

/**
 *
 * @author CRISTIANO
 */
public enum StatusVeiculo {

    ATIVO(1), NAO_ATIVO(2);

    private final int Valor;

    private StatusVeiculo(int valor) {

        Valor = valor;
    }

    /**
     * @return the Valor
     */
    public int getValor() {
        return Valor;
    }

}
