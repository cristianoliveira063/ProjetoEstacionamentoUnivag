
package br.com.java.builder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author CRISTIANO
 */
public class BbBoleto implements  Boleto{
    
   private final String sacado;
   private final String cedente;
   private final double valor;
   private final Calendar vencimento;
   private final int nossoNumero;

    public BbBoleto(String sacado,String cedente,double valor,Calendar vencimento,int nossoNumero) {
        this.sacado = sacado;
        this.cedente = cedente;
        this.valor = valor;
        this.vencimento = vencimento;
        this.nossoNumero = nossoNumero;
        
        
    }
    
  
    

    @Override
    public String getSacado() {
        return this.sacado;
        
    }

    @Override
    public String getCedente() {
        return this.cedente; 
    }

    @Override
    public double getValor() {
        return this.valor;
      
    }

    @Override
    public Calendar getVencimento() {
        return this.vencimento;
       
    }

    @Override
    public int getNossoNumero() {
        return this.nossoNumero;
       
    }
    
    
   @Override
    public String toString(){
        
        StringBuilder builder = new StringBuilder();
        builder.append("Boleto BB");
        builder.append("\n");
        builder.append("Sacado: ").append(this.sacado);
        builder.append("\n");
        builder.append("Cedente: ").append(this.cedente);
        builder.append("\n");
        builder.append("Valor: ").append(this.valor);
        builder.append("\n");
        builder.append("Vencimento: ").append(this.sacado);
        builder.append("\n");
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       String data = format.format(this.vencimento.getTime());
       builder.append("Vencimento: ").append(data);
       builder.append("\n");
       builder.append("Nosso Numero: ").append(this.nossoNumero);
       builder.append("\n");
        
        
        
        
        
        
       return builder.toString();
        
        
    }
    
}
