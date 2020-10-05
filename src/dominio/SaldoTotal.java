/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author Francisco
 */
public class SaldoTotal {
   private Double valorTotal ;
   private Double ingresoTotal;
   private Double egresoTotal;

    public SaldoTotal(Double ingresoTotal, Double egresoTotal) {
        
        this.ingresoTotal = ingresoTotal;
        this.egresoTotal = egresoTotal;
        this.valorTotal = ingresoTotal - egresoTotal;
    }

    public SaldoTotal() {
        this.ingresoTotal = 0.0;
        this.egresoTotal = 0.0;
        this.valorTotal = 0.0;
        
    }
    
    
   

  
   /*
    
   CalcularValorTotal(): Void
RegistrarSaldoDiario(): SaldoDiario*

    */

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(Double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    public Double getEgresoTotal() {
        return egresoTotal;
    }

    public void setEgresoTotal(Double egresoTotal) {
        this.egresoTotal = egresoTotal;
    }

   public Double calcularSaldoTotal(){
       this.valorTotal= this.ingresoTotal - this.egresoTotal;
       return this.valorTotal;
   }
}
