/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Francisco
 */
public class Fecha {
    
    public Date fecha;
    public ArrayList <Seminario> seminarios;
    public ArrayList <Sesion> sesiones;
    public SaldoDiario saldoDiario;
   

    public Fecha(Date fecha, ArrayList<Seminario> seminarios, ArrayList<Sesion> sesiones) {
        this.fecha = fecha;
        this.seminarios = new ArrayList <>();
        this.sesiones = new ArrayList <>();
        this.saldoDiario = new SaldoDiario();
        
    }

    public Fecha(Date fecha) {
        this.fecha = fecha;
        this.seminarios = new ArrayList <>();
        this.sesiones = new ArrayList <>();
        this.saldoDiario = new SaldoDiario();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   

    
    


    public ArrayList<Seminario> getSeminarios() {
        return seminarios;
    }

    public void setSeminarios(ArrayList<Seminario> seminarios) {
        this.seminarios = seminarios;
    }

    public ArrayList<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(ArrayList<Sesion> sesiones) {
        this.sesiones = sesiones;
    }
    
    
    
    /*RegistrarDatos(): Beneficiario*
    EditarDatos(): Beneficiario*
    EliminarBeneficiario(): Beneficiario*
    ActualizarListaAlumnos(): Alumno*
    CrearSeminario(): Seminario*
    EliminarSeminario(): Seminario*
    CrearSesion(): Sesion*
    EliminarSesion(): Sesion*/

    
}
