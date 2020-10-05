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
public class Beneficiario {
    
    private String nombre, telefono, correo, observaciones;
    private Date fechaIngreso;

    public Beneficiario(String nombre, String telefono, String correo, String observaciones, Date fechaIngreso) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.observaciones = observaciones;
        this.fechaIngreso = fechaIngreso;
    }

    public Beneficiario() {
    }
    

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

  

   
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    

}
