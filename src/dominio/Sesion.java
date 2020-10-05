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
public class  Sesion{

    public ArrayList <Beneficiario> asistentes;  
    public Date fecha;

    public Sesion(ArrayList<Beneficiario> asistentes, Date fecha) {
        this.asistentes = asistentes;
        this.fecha = fecha;
    }
    
     public Sesion(Date fecha) {
        this.fecha=fecha;
        this.asistentes = new ArrayList <>();
    }


    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

   
    
    
    public ArrayList<Beneficiario> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(ArrayList<Beneficiario> asistentes) {
        this.asistentes = asistentes;
    }

  
    
    
   
    
}
