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
public class Alumno extends Beneficiario {
    
    public boolean certificado;
    public int numeroSeminarios;
    public ArrayList <String> asistencias;

    public Alumno( String nombre, String telefono, String correo, String observaciones, Date fechaIngreso, boolean certificado, int numeroSeminarios) {
        super(nombre, telefono, correo, observaciones, fechaIngreso);
        this.certificado = certificado;
        this.numeroSeminarios = numeroSeminarios;
        this.asistencias = new ArrayList <>();
    }

    public boolean isCertificado() {
        return certificado;
    }

    public void setCertificado(boolean certificado) {
        this.certificado = certificado;
    }

   
    public int getNumeroSeminarios() {
        return numeroSeminarios;
    }

    public void setNumeroSeminarios(int numeroSeminarios) {
        this.numeroSeminarios = numeroSeminarios;
    }

    public ArrayList<String> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(ArrayList<String> asistencias) {
        this.asistencias = asistencias;
    }
    
/*RegistrarSeminarios() : Seminario*
VerificarCertificado(): Boolean
CalcularSeminarios(): Int*/
    
}
