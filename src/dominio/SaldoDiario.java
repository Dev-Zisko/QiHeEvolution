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
public class SaldoDiario {
    
    //public Double ingresoDia, egresodia, ingresoEfectivo, ingresoDebito, saldoDia;
    public ArrayList <Ingreso> ingresos;
    public ArrayList <Egreso> egresos;
  /*
    public SaldoDiario(Double ingresoDia, Double egresodia, Double ingresoEfectivo, Double ingresoDebito, Double saldoDia, ArrayList<Ingreso> ingresos, ArrayList<Egreso> egresos) {
      this.ingresoDia = ingresoDia;
        this.egresodia = egresodia;
        this.ingresoEfectivo = ingresoEfectivo;
        this.ingresoDebito = ingresoDebito;
        this.saldoDia = saldoDia;
        this.ingresos = new ArrayList <>();
        this.egresos = new ArrayList <>();
    }
*/
    public SaldoDiario() {
        this.ingresos = new ArrayList <>();
        this.egresos = new ArrayList <>();
    }
    
    
/*
    public Double getIngresoDia() {
        return ingresoDia;
    }

    public void setIngresoDia(Double ingresoDia) {
        this.ingresoDia = ingresoDia;
    }

    public Double getEgresodia() {
        return egresodia;
    }

    public void setEgresodia(Double egresodia) {
        this.egresodia = egresodia;
    }

    public Double getIngresoEfectivo() {
        return ingresoEfectivo;
    }

    public void setIngresoEfectivo(Double ingresoEfectivo) {
        this.ingresoEfectivo = ingresoEfectivo;
    }

    public Double getIngresoDebito() {
        return ingresoDebito;
    }

    public void setIngresoDebito(Double ingresoDebito) {
        this.ingresoDebito = ingresoDebito;
    }

    public Double getSaldoDia() {
        return saldoDia;
    }

    public void setSaldoDia(Double saldoDia) {
        this.saldoDia = saldoDia;
    }
*/
    public ArrayList<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(ArrayList<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    public ArrayList<Egreso> getEgresos() {
        return egresos;
    }

    public void setEgresos(ArrayList<Egreso> egresos) {
        this.egresos = egresos;
    }

   
    
   /* CalcularIngresoDia(): Void
    CalcularEgresoDia():Void
    CalcularIngreso():Void
    CalcularSaldoDia():Void
    RegistrarIngresos():Ingreso*
    RegistrarEgresos():Egreso*
*/
    
}
