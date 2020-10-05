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
public class Asociacion {
    
    private String nombre, telefono, direccion, rif;
    public SaldoTotal saldoTotal = new SaldoTotal();
    public ArrayList <Fecha> fechas;
    public ArrayList <Beneficiario> beneficiarios;
   

    public Asociacion(String nombre, String telefono, String direccion, String rif, SaldoTotal saldoTotal, ArrayList <Fecha> fechas, ArrayList <Beneficiario> beneficiarios) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rif = rif;
        this.saldoTotal = new SaldoTotal();
        this.fechas = new ArrayList<>();
        this.beneficiarios = new ArrayList <>();
        
    }

    public Asociacion(String nombre, String telefono, String direccion , String rif) {
        this.fechas = new ArrayList<>();
        this.beneficiarios = new ArrayList <>();
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.rif = rif;
        this.saldoTotal = new SaldoTotal();
        
       
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public SaldoTotal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(SaldoTotal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public ArrayList<Fecha> getFechas() {
        return fechas;
    }

    public void setFechas(ArrayList<Fecha> fechas) {
        this.fechas = fechas;
    }

    public ArrayList<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(ArrayList<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

   
    
   /* GestionarSaldoTotal():Void
    CalcularSaldo(): Double
    MostrarSaldo(): Void
    AdministrarBeneficiario():Void
    VerificarClase():boolean
    CambiarClase(): Alumno
    MostrarListaSeminario(): Void
    MostrarLista(): Void
    GestionarListas(): Void
    AdministrarSeminarios(): Void
    GenerarListaCorreo(): Void
    AdministrarSesiones(): Void
*/
}
