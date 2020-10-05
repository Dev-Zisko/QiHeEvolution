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
public class Ingreso {
    private Double totalEfectivo;
    private Double totalDebito;
    private Double total;
    private String razon, tipo;
    private Date fecha;

    public Ingreso(Double totalEfectivo, Double totalDebito, String razon, Date fecha) {
        
        this.totalEfectivo = totalEfectivo;
        this.totalDebito = totalDebito;
        this.total = totalEfectivo + totalDebito;
        this.razon = razon;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public Double getTotalEfectivo() {
        return totalEfectivo;
    }

    public void setTotalEfectivo(Double totalEfectivo) {
        this.totalEfectivo = totalEfectivo;
    }

    public Double getTotalDebito() {
        return totalDebito;
    }

    public void setTotalDebito(Double totalDebito) {
        this.totalDebito = totalDebito;
    }
    
    
}
