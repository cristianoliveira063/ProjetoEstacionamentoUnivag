package br.com.univag.dominio;

/**
 *
 * @author CRISTIANO
 */
public enum StatusMovimentoExibir {

    ATIVO(1), INATIVO(2);

    private final int Valor;

    private StatusMovimentoExibir(int valor) {

        Valor = valor;
    }

    /**
     * @return the Valor
     */
    public int getValor() {
        return Valor;
    }

}
