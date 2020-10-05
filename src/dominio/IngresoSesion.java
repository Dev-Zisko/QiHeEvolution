/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Date;

/**
 *
 * @author Francisco
 */
public class IngresoSesion extends Ingreso{
    
  
    private Double precio;
    private int efectivo, debito, asistentes;
    private String razon;

    public IngresoSesion(Double precio, int efectivo, int debito, Double totalEfectivo, Double totalDebito,String razon, Date fecha) {
        super(totalEfectivo, totalDebito, razon ="Sesión", fecha);
        
        this.precio = precio;
        this.efectivo = efectivo;
        this.debito = debito;
        this.asistentes = this.debito+this.efectivo;
    }

    public int getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(int asistentes) {
        this.asistentes = asistentes;
    }

   

    public int getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(int efectivo) {
        this.efectivo = efectivo;
    }

    public int getDebito() {
        return debito;
    }

    public void setDebito(int debito) {
        this.debito = debito;
    }

   
    

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
   

    
}
