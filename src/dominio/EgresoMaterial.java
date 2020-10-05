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
public class EgresoMaterial extends Egreso{
    
    public String nombreMaterial;
    public Double precio;
    public int cantidad;

    public EgresoMaterial(String nombreMaterial, Double precio, int cantidad, Double valorTotal, Date fecha, String razon) {
        super(valorTotal, fecha, razon = "Materiales");
        this.nombreMaterial = nombreMaterial;
        this.precio = precio;
        this.cantidad = cantidad;
    }

        
    

    public String getNombrematerial() {
        return nombreMaterial;
    }

    public void setNombrematerial(String nombrematerial) {
        this.nombreMaterial = nombrematerial;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
    
}
